package com.theappbusiness.marvel;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.Html;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by demir on 9.04.2017.
 */

public class Utils {
    public static void hideKeyboard(Context context, View view) {
        view.clearFocus();
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @TargetApi(Build.VERSION_CODES.N)
    public static String convertHtmlTextNougat(String html)
    {
        return Html.fromHtml(html, Html.FROM_HTML_MODE_COMPACT)+"";
    }

    public static String convertHtmlText(String html)
    {
        return Html.fromHtml(html)+"";
    }

}
