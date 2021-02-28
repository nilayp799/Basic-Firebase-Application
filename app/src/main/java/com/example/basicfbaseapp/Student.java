package com.example.basicfbaseapp;

public class Student {
    String name,enrollment;
    public Student (String name,String enrollment){
        this.name = name;
        this.enrollment = enrollment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEnrollment() {
        return enrollment;
    }

    public void setEnrollment(String enrollment) {
        this.enrollment = enrollment;
    }
}
