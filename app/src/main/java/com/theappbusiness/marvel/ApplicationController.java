package com.theappbusiness.marvel;


import android.app.Application;

import com.orm.SugarContext;
import com.theappbusiness.marvel.network.NetworkManager;

/**
 * Created by demir on 29.08.2016.
 */
public class ApplicationController extends Application {
    private static ApplicationController mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        NetworkManager.initialize(getApplicationContext());
        SugarContext.init(getApplicationContext());
    }

    public static ApplicationController getInstance()
    {
        return mInstance;
    }


}
