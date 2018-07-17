package com.pabloquijano.loginpokedex.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.afollestad.materialdialogs.MaterialDialog;
import com.pabloquijano.loginpokedex.R;

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

    //Retuns Font Family
    public Typeface getFontRegular(){
        return font_regular;
    }

    public Typeface getFontSBold() {
        return font_sbold;
    }

    //Shows a Dialog Error
    public MaterialDialog getErrorDialog(String msg, Context context){

        return new MaterialDialog.Builder(context)
                .title(R.string.error)
                .typeface(font_sbold, font_regular)
                .titleColorRes(R.color.red)
                .contentColorRes(R.color.ColorPrimaryText)
                .content(msg)
                .cancelable(false)
                .positiveText(R.string.ok)
                .show();
    }

    //Shows a Dialog Error for Fark Theme
    public MaterialDialog getErrorDialogDark(String msg, Context context){

        return new MaterialDialog.Builder(context)
                .title(R.string.error)
                .typeface(font_sbold, font_regular)
                .titleColorRes(R.color.red)
                .contentColorRes(R.color.colorPrimaryLight)
                .positiveColorRes(R.color.white)
                .content(msg)
                .cancelable(false)
                .positiveText(R.string.ok)
                .show();
    }

    //Shows Progress Dialog
    public MaterialDialog getProgressDialog(String msg, Context context){
        return new MaterialDialog.Builder(context)
                .title(msg)
                .typeface(font_sbold, font_regular)
                .content(R.string.wait)
                .titleColorRes(R.color.colorPrimary)
                .contentColorRes(R.color.ColorPrimaryText)
                .cancelable(false)
                .progress(true, 0)
                .show();
    }

    //Shows Progress Dialog for Dark Theme
    public MaterialDialog getProgressDialogDark(String msg, Context context){
        return new MaterialDialog.Builder(context)
                .title(msg)
                .typeface(font_sbold, font_regular)
                .content(R.string.wait)
                .titleColorRes(R.color.white)
                .contentColorRes(R.color.colorPrimaryLight)
                .cancelable(false)
                .progress(true, 0)
                .show();
    }

    //Shows confirmation dialog
    public MaterialDialog.Builder getQuestionDialog(String title, String msg, Context context){

        return new MaterialDialog.Builder(context)
                .title(title)
                .typeface(font_sbold, font_regular)
                .titleColorRes(R.color.colorPrimary)
                .contentColorRes(R.color.ColorPrimaryText)
                .content(msg)
                .cancelable(true)
                .positiveText(R.string.ok)
                .negativeText(R.string.cancel);
    }
}
