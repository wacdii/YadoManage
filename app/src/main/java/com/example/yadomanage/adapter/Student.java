package com.example.yadomanage.adapter;

import java.io.Serializable;

public class Student implements Serializable{
    String sid,s_name,s_dob,s_phone,s_email,s_address,  s_hometown, s_images, s_passwrod,cid;
    //int s_images;

    public Student(String sid, String s_name, String s_images) {
        this.sid = sid;
        this.s_name = s_name;
        this.s_images = s_images;
    }

    public Student(String sid, String s_name, String s_dob, String s_phone, String s_email, String s_address, String s_hometown, String s_images, String s_passwrod, String cid) {
        this.sid = sid;
        this.s_name = s_name;
        this.s_dob = s_dob;
        this.s_phone = s_phone;
        this.s_email = s_email;
        this.s_address = s_address;
        this.s_hometown = s_hometown;
        this.s_images = s_images;
        this.s_passwrod = s_passwrod;
        this.cid = cid;
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

    public String getS_dob() {
        return s_dob;
    }

    public void setS_dob(String s_dob) {
        this.s_dob = s_dob;
    }

    public String getS_phone() {
        return s_phone;
    }

    public void setS_phone(String s_phone) {
        this.s_phone = s_phone;
    }

    public String getS_email() {
        return s_email;
    }

    public void setS_email(String s_email) {
        this.s_email = s_email;
    }

    public String getS_address() {
        return s_address;
    }

    public void setS_address(String s_address) {
        this.s_address = s_address;
    }

    public String getS_hometown() {
        return s_hometown;
    }

    public void setS_hometown(String s_hometown) {
        this.s_hometown = s_hometown;
    }

    public String getS_images() {
        return s_images;
    }

    public void setS_images(String s_images) {
        this.s_images = s_images;
    }

    public String getS_passwrod() {
        return s_passwrod;
    }

    public void setS_passwrod(String s_passwrod) {
        this.s_passwrod = s_passwrod;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }
}
