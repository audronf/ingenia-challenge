package com.ingenia.stations.models;

public class Station {

    private Long id;

    private String name;

    public Station() {
    }

    public Station(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
