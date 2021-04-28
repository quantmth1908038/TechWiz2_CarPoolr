package com.techwiz2.carpoolr.model;

import java.util.Date;

public class History {
    private double destinationX;
    private double destinationY;
    private double departureX;
    private double departureY;
    private String status;
    private long time;
    private double fare;
    private Car cars;
    private String fromAdd;
    private String toAdd;
    private String Direction;

    public Car getCar() {
        return cars;
    }

    public void setCar(Car car) {
        this.cars = car;
    }

    public String getDirection() {
        return Direction;
    }

    public void setDirection(String direction) {
        Direction = direction;
    }

    public String getFromAdd() {
        return fromAdd;
    }

    public void setFromAdd(String fromAdd) {
        this.fromAdd = fromAdd;
    }

    public String getToAdd() {
        return toAdd;
    }

    public void setToAdd(String toAdd) {
        this.toAdd = toAdd;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public double getFare() {
        return fare;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }
}
