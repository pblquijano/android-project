package com.pabloquijano.loginpokedex.utils;

import android.content.Context;
import android.graphics.Typeface;

public class Singleton {
    private static Singleton ourInstance = null;
    private Typeface font_regular, font_sbold;
    public static Singleton getInstance( Context context) {
        if(ourInstance == null)
        {
            ourInstance = new Singleton(context);
        }
        return ourInstance;
    }
    private Singleton(Context context) {
        font_regular = Typeface.createFromAsset(context.getAssets(), "Montserrat-Regular.ttf");
        font_sbold = Typeface.createFromAsset(context.getAssets(), "Montserrat-SemiBold.otf");
    }

    public Typeface getFontRegular(){
        return font_regular;
    }

    public Typeface getFontSBold() {
        return font_sbold;
    }
}
