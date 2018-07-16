package com.pabloquijano.loginpokedex.models;

import com.google.gson.annotations.SerializedName;

public class Pokemon_data {
    @SerializedName("url")
    private String url;
    @SerializedName("name")
    private String name;

    private int id;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
