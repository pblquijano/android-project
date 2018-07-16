package com.pabloquijano.loginpokedex.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pokemon_response {
    @SerializedName("results")
    private List<Pokemon_data> results;

    public List<Pokemon_data> getResults() {
        return results;
    }
}
