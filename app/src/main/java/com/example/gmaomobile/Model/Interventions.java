package com.example.gmaomobile.Model;

import java.io.Serializable;
import java.util.Date;

public class Interventions implements Serializable {


    private int id;
    private String title;
    private String priority;
    private Date date;
    private String equipment;
    private String description;

    public Interventions() {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.equipment = equipment;
        this.description = description;
    }

    public Interventions(int id, String title, String priority, Date date, String equipment, String description) {
        this.id = id;
        this.title = title;
        this.priority = priority;
        this.date = date;
        this.equipment = equipment;
        this.description = description;
    }


    public  int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEquipment() {
        return equipment;
    }

    public void setEquipment(String equipment) {
        this.equipment = equipment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDate(String string) {
    }
}







