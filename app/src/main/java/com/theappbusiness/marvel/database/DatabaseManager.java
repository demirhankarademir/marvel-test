package com.theappbusiness.marvel.database;

import com.orm.SugarRecord;
import com.orm.query.Condition;
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
        List<Creator> creatorList = new ArrayList<>();

        for (NetworkModels.GetComicsResponse.Comic comic : comics)
        {
            Comic c = new Comic();
            c.mId = comic.id;
            c.setImage(comic.thumbnail);
            c.title = comic.title;
            c.description = comic.description;
            c.pageCount = comic.pageCount;
            c.price = comic.prices[0].price;

            for (NetworkModels.GetComicsResponse.Creator.Item creator : comic.creators.items)
            {
                String creatorID = comic.id+creator.name;
                Creator cr = new Creator(creatorID, creator.name, creator.role, c);
                creatorList.add(cr);
            }

            comicList.add(c);
        }

        SugarRecord.saveInTx(comicList);
        SugarRecord.saveInTx(creatorList);
    }

    public static List<Comic> getComics()
    {
        return Select.from(Comic.class)
//                .orderBy("title")
                .list();
    }

    public static Comic getComic(int comicID)
    {
        return Select.from(Comic.class).where(Condition.prop("m_id").eq(comicID)).first();
    }

    public static List<Creator> getCreators()
    {
        return Select.from(Creator.class).list();
    }

}
