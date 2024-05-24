package com.ingenia.stations.models;

import java.util.UUID;

public class Station {

    private UUID id;

    private String name;

    public Station() {
    }

    public Station(String name) {
        this.id = UUID.randomUUID();
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
