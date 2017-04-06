package com.theappbusiness.marvel.network;

import com.google.gson.annotations.SerializedName;

/**
 * Created by demir on 5.04.2017.
 */

public class NetworkModels {

    public static class GetComicsResponse
    {
        @SerializedName("data")
        public Data data;

        public class Data
        {
            @SerializedName("results")
            public Comic[] results;
        }

        public class Comic
        {
            @SerializedName("id")
            public Integer id;
            @SerializedName("title")
            public String title;
            @SerializedName("thumbnail")
            public Image thumbnail;
        }
    }

    public static class Image
    {
        @SerializedName("path")
        public String path;
        @SerializedName("extension")
        public String extension;
    }
}
