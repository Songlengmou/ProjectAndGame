package com.example.syp.makecarapp;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by syp on 17-6-4.
 * <p>
 * 屏幕适配(计算尺寸)
 */

public class Config {

    private static final int CARD_WIDTH_IN_DP = 60;
    private static final int CARD_HEIGHT_IN_DP = 60;


    private static int _cardWidthInPx, _cardHeightInPx, _textSize;

    public static void initConfig(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        _cardWidthInPx = (int) (displayMetrics.density * CARD_WIDTH_IN_DP); //取得卡片所适应的手机屏幕宽度
        _cardHeightInPx = (int) (displayMetrics.density * CARD_HEIGHT_IN_DP);
        _textSize = (int) (0.8 * _cardHeightInPx);
    }

    public static int getCardWidthInPx() {
        return _cardWidthInPx;
    }

    public static int getCardHeightInPx() {
        return _cardHeightInPx;
    }

    public static int getTextSize() {
        return _textSize;
    }
}
