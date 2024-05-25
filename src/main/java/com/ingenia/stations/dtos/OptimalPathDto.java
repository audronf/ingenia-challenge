package com.ingenia.stations.dtos;

import com.ingenia.stations.models.Station;

import java.util.List;

public class OptimalPathDto {

    public List<Station> path;

    public Double cost;

    public OptimalPathDto(){
    }

    public OptimalPathDto(List<Station> path, Double cost) {
        this.path = path;
        this.cost = cost;
    }
}
