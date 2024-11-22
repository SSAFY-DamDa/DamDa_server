package com.ssafy.journey.model;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.trip.model.TripDto;

public class JourneyDto {
    private int id; // journeyId 필드 추가
    private String title;
    private String startDate;
    private String endDate;
    private int personnel;
    private String color;
    // Getters and Setters
    public int getId() {
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getPersonnel() {
        return personnel;
    }

    public void setPersonnel(int personnel) {
        this.personnel = personnel;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "JourneyDto [id=" + id + ", title=" + title +  ", startDate=" + startDate + ", endDate=" + endDate
                + ", personnel=" + personnel + ", color=" + color +  "]";
    }
}
