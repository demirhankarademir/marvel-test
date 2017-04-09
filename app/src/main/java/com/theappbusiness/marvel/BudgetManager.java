package com.theappbusiness.marvel;

/**
 * Created by demir on 9.04.2017.
 */
public class BudgetManager {
    private static BudgetManager ourInstance = new BudgetManager();

    private float budget;

    public static BudgetManager getInstance() {
        return ourInstance;
    }

    private BudgetManager() {
    }

    public void setBudget(float budget)
    {
        Prefs.setBudget(budget);
    }

    public float getBudget() {
        return Prefs.getBudget();
    }
}
