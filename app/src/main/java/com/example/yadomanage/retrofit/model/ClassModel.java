package com.example.yadomanage.retrofit.model;

import com.google.gson.annotations.SerializedName;

public class ClassModel {
    @SerializedName("status")
    private String status;
    @SerializedName("result_code")
    private int result_code;
    @SerializedName("c_id")
    private String cid;
    @SerializedName("c_name")
    private String cname;
    @SerializedName("c_linkgroup")
    private String clinkgroup;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getResult_code() {
        return result_code;
    }

    public void setResult_code(int result_code) {
        this.result_code = result_code;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getClinkgroup() {
        return clinkgroup;
    }

    public void setClinkgroup(String clinkgroup) {
        this.clinkgroup = clinkgroup;
    }
}
