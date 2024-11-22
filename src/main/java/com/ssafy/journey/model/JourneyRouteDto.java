package com.ssafy.journey.model;

public class JourneyRouteDto {
    private int journeyId;
    private int day;
    private int orderInDay;
    private int contentId;

    // Getters and Setters
    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getOrderInDay() {
        return orderInDay;
    }

    public void setOrderInDay(int orderInDay) {
        this.orderInDay = orderInDay;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    @Override
    public String toString() {
        return "JourneyRouteDto [journeyId=" + journeyId + ", day=" + day + ", orderInDay=" + orderInDay + ", contentId=" + contentId + "]";
    }
}
