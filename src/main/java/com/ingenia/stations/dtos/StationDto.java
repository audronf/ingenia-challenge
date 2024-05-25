package com.ingenia.stations.dtos;

public class StationDto {

    public Long id;

    public String name;

    public StationDto(){
    }

    public StationDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
