package com.ingenia.stations.models;

import java.util.UUID;

public class Path {

    private long id;

    private Station origin;

    private Station destination;

    private double cost;

    public Path() {
    }

    public Path(long id, Station origin, Station destination, double cost) {
        this.origin = origin;
        this.destination = destination;
    }

    public Station getOrigin() {
        return origin;
    }

    public void setOrigin(Station origin) {
        this.origin = origin;
    }

    public Station getDestination() {
        return destination;
    }

    public void setDestination(Station destination) {
        this.destination = destination;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
