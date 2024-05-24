package com.a3psc;

import java.util.Date;

public class Event {
    private int id;
    private String name;
    private String description;
    private Date startDate;
    private Date endDate;

    public int getId() {
        return id;
    }

    public int setId(int id) {
        this.id = id;
    }

    public String getName(){
        return name;
    }

    public String setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return description;
    }

    public String setDescription(String description){
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}


