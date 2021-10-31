package com.example.yadomanage.newsfeed;

public class Newsfeed {
    String nf_content,nf_time,tid,cid;

    public Newsfeed(String nf_content, String nf_time, String tid, String cid) {
        this.nf_content = nf_content;
        this.nf_time = nf_time;
        this.tid = tid;
        this.cid = cid;
    }

    public String getNf_content() {
        return nf_content;
    }

    public void setNf_content(String nf_content) {
        this.nf_content = nf_content;
    }

    public String getNf_time() {
        return nf_time;
    }

    public void setNf_time(String nf_time) {
        this.nf_time = nf_time;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
