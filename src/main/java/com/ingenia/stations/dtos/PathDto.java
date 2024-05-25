package com.ingenia.stations.dtos;

public class PathDto {

    public Long pathId;

    public Long sourceId;

    public Long destinationId;

    public double cost;

    public PathDto() {
    }

    public PathDto(Long pathId, Long sourceId, Long destinationId, double cost) {
        this.pathId = pathId;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.cost = cost;
    }
}
