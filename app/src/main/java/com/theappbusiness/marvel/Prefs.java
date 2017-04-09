package com.theappbusiness.marvel;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by demir on 3.10.2016.
 */
public class Prefs {
    private static Context context;

    public static void initialize(Context _context) {
        context = _context;
    }

    private static SharedPreferences getPrefs() {
        return context.getSharedPreferences("marvel", Context.MODE_PRIVATE);
    }

    private static final String BUDGET = "budget";


    public static float getBudget()
    {
        return getPrefs().getFloat(BUDGET, 0);
    }

    public static void setBudget(float budget)
    {
        getPrefs().edit().putFloat(BUDGET, budget).apply();
    }

}

