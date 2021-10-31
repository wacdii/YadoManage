package com.example.yadomanage.chat;

public class Message {
    private String m_content;
    private String m_time;
    private String sid;
    private String tid;
    private String au;

    public Message(String m_content, String m_time, String sid, String tid, String au) {
        this.m_content = m_content;
        this.m_time = m_time;
        this.sid = sid;
        this.tid = tid;
        this.au = au;
    }

    public String getM_content() {
        return m_content;
    }

    public void setM_content(String m_content) {
        this.m_content = m_content;
    }

    public String getM_time() {
        return m_time;
    }

    public void setM_time(String m_time) {
        this.m_time = m_time;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getAu() {
        return au;
    }

    public void setAu(String au) {
        this.au = au;
    }
}
