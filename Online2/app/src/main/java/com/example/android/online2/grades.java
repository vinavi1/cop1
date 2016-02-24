package com.example.android.online2;

/**
 * Created by VIVEK on 24-02-2016.
 */
public class grades {
    private int id;
    private String course;
    private String weightage;
    private String descr;
    private String name;

    public grades(int id,String course,String weightage,String descr,String name){
        this.id=id;
        this.course=course;
        this.weightage=weightage;
        this.descr=descr;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getWeightage() {
        return weightage;
    }

    public void setWeightage(String weightage) {
        this.weightage = weightage;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }
}
