package com.theappbusiness.marvel.database;

import com.orm.SugarRecord;
import com.orm.dsl.Ignore;
import com.orm.dsl.Unique;
import com.orm.query.Condition;
import com.orm.query.Select;
import com.theappbusiness.marvel.network.NetworkModels;

import java.util.List;

/**
 * Created by demir on 6.04.2017.
 */

public class Comic extends SugarRecord {

    @Ignore
    private static final String IMAGE_VARIANT_THUMBNAIL = "portrait_medium";
    @Ignore
    private static final String IMAGE_VARIANT_BIG = "landscape_incredible";

    @Unique
    int mId;

    String image;
    String imageExtention;
    String title;
    String description;
    Integer pageCount;
    Double price;

    public Comic(){

    }

    public void setImage(NetworkModels.Image image)
    {
        this.image = image.path;
        this.imageExtention = image.extension;
    }



    public String getImageThumbnail() {
        return image + "/" + IMAGE_VARIANT_THUMBNAIL + "."+ imageExtention;
    }

    public String getImageBigLandscape() {
        return image + "/" + IMAGE_VARIANT_BIG + "."+ imageExtention;
    }

    public String getTitle() {
        return title;
    }

    public List<Creator> getCreators()
    {
//        return Creator.find(Creator.class, "comic = ?", mId +"");
        return Select.from(Creator.class)
                .where(Condition.prop("comic").eq(this))
                .list();
    }

    public int getmId()
    {
        return mId;
    }

    public String getDescription() {
        return description;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }
}
