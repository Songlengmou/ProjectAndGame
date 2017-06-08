package com.example.syp.makecarapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.PointF;

import java.util.ArrayList;
import java.util.List;

import top.yunp.androidgame2d.display.Display;
import top.yunp.androidgame2d.display.GameView;
import top.yunp.androidgame2d.events.TouchEvent;
import top.yunp.lib.java.event.EventListener;


/**
 * Created by syp on 17-6-4.
 * <p>
 * 数字卡片记忆游戏
 * <p>
 * 主页
 */

public class MainView extends GameView {

    private List<PointF> points = new ArrayList<>();//转换 PointF 按指定大小的负值
    private int currentNum = 1;
    private List<NumberCard> cards = new ArrayList<>();
    private int level = 3, topLevel = 6;

    public MainView(Context context) {
        super(context);

        Config.initConfig(context);

        shouldStartGame();
    }

    //开始游戏前的提醒
    private void shouldStartGame() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.welcome)
                .setMessage(R.string.click_start_button_to_start_game)
                .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();  //重新开始游戏
                    }
                })
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                })
                .setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        getActivity().finish();
                    }
                }).show();
    }

    private void restartGame() {
        level = 3;//在这一关提供了3个卡片

        startGameByCurrentLevel();//根据卡片数量启动游戏
    }

    private void startGameByCurrentLevel() {
        currentNum = 1;
        clearGameData();//清空游戏
        addCards();//向视图中添加卡片
    }

    private void clearGameData() {
        while (cards.size() > 0) {
            cards.remove(0).removeFromParent();
        }
        //重置坐标点数组
        points.clear();
    }

    /**
     * 向视图中添加卡片
     */
    private void addCards() {
        int hCount = getWidth() / Config.getCardWidthInPx();
        int vCount = getHeight() / Config.getCardHeightInPx();//垂直方向的最大卡片数量
        topLevel = hCount * vCount;


        float cardsMapWidth = hCount * Config.getCardWidthInPx();
        float cardsMapHeight = vCount * Config.getCardHeightInPx();
        float xRemain = getWidth() - cardsMapWidth;
        float yRemain = getHeight() - cardsMapHeight;
        float globalOffsetX = Config.getCardWidthInPx() / 2 + xRemain / 2;
        float globalOffsetY = Config.getCardHeightInPx() / 2 + yRemain / 2;

        //生成可用坐标点
        for (int i = 0; i < hCount; i++) {
            for (int j = 0; j < vCount; j++) {
                points.add(new PointF(i * Config.getCardWidthInPx() + globalOffsetX, j * Config.getCardHeightInPx() + globalOffsetY));
            }
        }

        PointF p;
        NumberCard nc;
        //从可用坐标点中取得10个坐标点，用于设置卡片的位置
        for (int i = 1; i <= level; i++) {
            p = points.remove((int) (points.size() * Math.random()));
            nc = new NumberCard(i);
            nc.setX(p.x).setY(p.y);
            add(nc);
            cards.add(nc);
            nc.touchDown.add(ncTouchDownHandler);
        }
    }

    //TouchEvent 是一类描述手指在触摸平面(触摸屏、触摸板等)的状态变化的事件。
    // 这类事件用于描述一个或多个触点,使开发者可以检测触点的移动,触点的增加和减少。
    private EventListener<TouchEvent, Display> ncTouchDownHandler = new EventListener<TouchEvent, Display>() {
        @Override
        public boolean onReceive(TouchEvent event, Display display) {
            if (display instanceof NumberCard) {
                if (((NumberCard) display).getNumber() == currentNum) {
                    remove(display);
                    cards.remove(display);

                    //将所有卡片翻转到反面
                    if (currentNum == 1) {
                        for (NumberCard c : cards) {
                            c.turnToVerso();
                        }
                    }
                    currentNum++;

                    //当卡片数组中数量为0时，则意味着你完成了游戏
                    if (cards.size() <= 0) {
                        if (level < topLevel) {
                            showGoToNextLevelDialog();
                        } else {
                            //通关
                            showWinDialog();
                        }
                    }
                } else {
                    showFailDialog();
                }
            }
            return false;
        }
    };

    //游戏失败的提醒
    private void showFailDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.note)
                .setMessage(R.string.number_wrong_game_will_restart)
                .setCancelable(false)
                .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                }).show();
    }

    //游戏赢了
    private void showWinDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.congratulations)
                .setMessage(R.string.you_win)
                .setPositiveButton(R.string.replay_again, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        restartGame();
                    }
                })
                .setNegativeButton(R.string.close, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getActivity().finish();
                    }
                }).show();
    }

    //进入下一关
    private void showGoToNextLevelDialog() {
        new AlertDialog.Builder(getContext())
                .setTitle(R.string.note)
                .setMessage(getContext().getString(R.string.game_will_go_to_next_level_it_will_show))
                .setCancelable(false)
                .setPositiveButton(R.string.start, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        nextLevel();
                    }
                }).show();
    }

    //下一关卡片的数量
    private void nextLevel() {
        level++;
        if (level <= topLevel) {
            startGameByCurrentLevel();
        }
    }

    public MainActivity getActivity() {
        return (MainActivity) getContext();
    }
}
