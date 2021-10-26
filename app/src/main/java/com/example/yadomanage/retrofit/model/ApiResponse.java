package com.example.yadomanage.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class ApiResponse {
    @SerializedName("status")
    private String status;
    @SerializedName("result_code")
    private int result_code;
    @SerializedName("name")
    private String name;
    @SerializedName("id")
    private String id;

    public String getStatus() {
        return status;
    }

    public int getResult_code() {
        return result_code;
    }

    public String getName() {
        return name;
    }

    public String getId() { return id; }
}
