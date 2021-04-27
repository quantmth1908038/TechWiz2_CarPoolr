package com.techwiz2.carpoolr.model;

import java.util.Date;

public class History {
    private double destinationX;
    private double destinationY;
    private double departureX;
    private double departureY;
    private String status;
    private Date time;
    private double fare;
    private int slot;
    private String name;
    private String plate;

    public double getDestinationX() {
        return destinationX;
    }

    public void setDestinationX(double destinationX) {
        this.destinationX = destinationX;
    }

    public double getDestinationY() {
        return destinationY;
    }

    public void setDestinationY(double destinationY) {
        this.destinationY = destinationY;
    }

    public double getDepartureX() {
        return departureX;
    }

    public void setDepartureX(double departureX) {
        this.departureX = departureX;
    }

    public double getDepartureY() {
        return departureY;
    }

    public void setDepartureY(double departureY) {
        this.departureY = departureY;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }
}
