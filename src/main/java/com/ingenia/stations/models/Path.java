package com.ingenia.stations.models;

import java.util.UUID;

public class Path {

    private UUID id;

    private Station origin;

    private Station destination;

    public Path() {
    }

    public Path(Station origin, Station destination) {
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
}
