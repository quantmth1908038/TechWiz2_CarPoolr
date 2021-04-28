package com.techwiz2.carpoolr.model;

import java.util.Date;

public class Ride {
    private int ride_id;
    private double destinationX;
    private double destinationY;
    private double departureX;
    private double departureY;
    private int slot;
    private String status;
    private Date time;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
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

    public int getRide_id() {
        return ride_id;
    }

    public void setRide_id(int ride_id) {
        this.ride_id = ride_id;
    }

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
}
