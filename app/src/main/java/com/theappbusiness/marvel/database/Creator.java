package com.theappbusiness.marvel.database;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;
import com.theappbusiness.marvel.network.NetworkModels;

/**
 * Created by demir on 6.04.2017.
 */

public class Creator extends SugarRecord {

    @Unique
    String creatorId;

    String name;
    String role;
    Comic comic;

    public Creator(){
    }

    public Creator(String creatorId, String name, String role, Comic comic)
    {
        this.creatorId = creatorId;
        this.name = name;
        this.role = role;
        this.comic = comic;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public Comic getComic() {
        return comic;
    }
}
