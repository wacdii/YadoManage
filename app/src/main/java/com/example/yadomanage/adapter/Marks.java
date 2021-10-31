package com.example.yadomanage.adapter;

public class Marks {
    private String sid, s_name,r_trainmark,r_avemark, r_semmark;

    public Marks(String sid, String s_name,String r_avemark, String r_semmark, String r_trainmark) {
        this.sid = sid;
        this.s_name = s_name;
        this.r_trainmark = r_trainmark;
        this.r_avemark = r_avemark;
        this.r_semmark = r_semmark;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getR_trainmark() {
        return r_trainmark;
    }

    public void setR_trainmark(String r_trainmark) {
        this.r_trainmark = r_trainmark;
    }

    public String getR_avemark() {
        return r_avemark;
    }

    public void setR_avemark(String r_avemark) {
        this.r_avemark = r_avemark;
    }

    public String getR_semmark() {
        return r_semmark;
    }

    public void setR_semmark(String r_semmark) {
        this.r_semmark = r_semmark;
    }
}