package com.example.yadomanage.adapter;

public class Class {
    String cid, c_name, clink, tid;

    public Class(String cid, String cname, String clink, String tid) {
        this.cid = cid;
        this.c_name = cname;
        this.clink = clink;
        this.tid = tid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getCname() {
        return c_name;
    }

    public void setCname(String cname) {
        this.c_name = cname;
    }

    public String getClink() {
        return clink;
    }

    public void setClink(String clink) {
        this.clink = clink;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }
}
