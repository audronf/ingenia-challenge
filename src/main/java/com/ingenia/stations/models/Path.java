package com.ingenia.stations.models;

public class Path {

    private Long id;

    private Station origin;

    private Station destination;

    private double cost;

    public Path() {
    }

    public Path(long id, Station origin, Station destination, double cost) {
        this.id = id;
        this.origin = origin;
        this.destination = destination;
        this.cost = cost;
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
