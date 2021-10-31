package com.example.yadomanage.adapter;

public class Mcondition {
    String avemarks,semmarks,trainmarks;

    public Mcondition(String avemarks, String semmarks, String trainmarks) {
        this.avemarks = avemarks;
        this.semmarks = semmarks;
        this.trainmarks = trainmarks;
    }



    public String getAvemarks() {
        return avemarks;
    }

    public void setAvemarks(String avemarks) {
        this.avemarks = avemarks;
    }

    public String getSemmarks() {
        return semmarks;
    }

    public void setSemmarks(String semmarks) {
        this.semmarks = semmarks;
    }

    public String getTrainmarks() {
        return trainmarks;
    }

    public void setTrainmarks(String trainmarks) {
        this.trainmarks = trainmarks;
    }
}
