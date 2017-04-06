package com.theappbusiness.marvel.database;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;
import com.theappbusiness.marvel.network.NetworkModels;

/**
 * Created by demir on 6.04.2017.
 */

public class Comic extends SugarRecord {

    @Ignore
    private static final String IMAGE_VARIANT = "portrait_medium";

    @Unique
    int mID;

    String thumbnail;
    String title;

    public Comic(){
    }

    public void setThumbnail(NetworkModels.Image thumbnail)
    {
        String url = thumbnail.path + "/" + IMAGE_VARIANT + "."+ thumbnail.extension;
        this.thumbnail = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getTitle() {
        return title;
    }
}
