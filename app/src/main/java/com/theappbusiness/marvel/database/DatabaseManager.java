package com.theappbusiness.marvel.database;

import com.orm.SugarRecord;
import com.orm.query.Select;
import com.theappbusiness.marvel.network.NetworkModels;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demir on 6.04.2017.
 */

public class DatabaseManager
{
    public static void updateComics(NetworkModels.GetComicsResponse.Comic[] comics)
    {
        List<Comic> comicList = new ArrayList<>();
        for (NetworkModels.GetComicsResponse.Comic comic : comics)
        {
            Comic c = new Comic();
            c.mID = comic.id;
            c.setThumbnail(comic.thumbnail);
            c.title = comic.title;
            comicList.add(c);
        }

        SugarRecord.saveInTx(comicList);
    }

    public static List<Comic> getComics()
    {
        return Select.from(Comic.class)
                .orderBy("title")
                .list();


//        return Comic.listAll(Comic.class).sort();
    }
}
