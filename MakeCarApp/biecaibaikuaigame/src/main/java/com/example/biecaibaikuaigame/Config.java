package com.example.biecaibaikuaigame;

/**
 * Created by syp on 17-6-5.
 */

public class Config {
    private static int screenWidth, svcreenHeight, cardWidth, cardHeight;

    public static int getScreenWidth() {
        return screenWidth;
    }

    public static void setScreenWidth(int screenWidth) {
        Config.screenWidth = screenWidth;
    }

    public static int getSvcreenHeight() {
        return svcreenHeight;
    }

    public static void setSvcreenHeight(int svcreenHeight) {
        Config.svcreenHeight = svcreenHeight;
    }

    public static int getCardWidth() {
        return cardWidth;
    }

    public static void setCardWidth(int cardWidth) {
        Config.cardWidth = cardWidth;
    }

    public static int getCardHeight() {
        return cardHeight;
    }

    public static void setCardHeight(int cardHeight) {
        Config.cardHeight = cardHeight;
    }
}
