package top.yunp.androidgame2d.display;

import android.graphics.Path;

import top.yunp.androidgame2d.display.Shape;

/**
 * Created by syp on 17-6-5.
 *
 * 用在 biecaibaikuaigame里
 */

public class Rectangle extends Shape {

    private float width = 100, height = 100;
    private int color = 0xffff0000;
    private int bolderWidth = 0;//边框宽度
    private int bolderColor = 0xffffffff;//边框颜色

    public Rectangle(float width, float height, int color) {
        setWidth(width);
        setHeight(height);
        setColor(color);
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
        refreshRectangle();
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
        refreshRectangle();
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        getPaint().setColor(color);
    }

    public int getBolderWidth() {
        return bolderWidth;
    }

    public void setBolderWidth(int bolderWidth) {
        this.bolderWidth = bolderWidth;
        refreshRectangle();
    }

    public int getBolderColor() {
        return bolderColor;
    }

    public void setBolderColor(int bolderColor) {
        this.bolderColor = bolderColor;

        //TODO implement the border color
    }

    //刷新长方形
    public void refreshRectangle() {
        getPath().reset();
        getPath().addRect(getBolderWidth(), getBolderWidth(), getWidth() - getBolderWidth(), getHeight() - getBolderWidth(), Path.Direction.CW);
        getPath().close();
    }


}
