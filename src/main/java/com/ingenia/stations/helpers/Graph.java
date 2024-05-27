package com.ingenia.stations.helpers;

import com.ingenia.stations.models.Path;
import com.ingenia.stations.models.Station;

import java.util.List;

public class Graph {
    private final List<Station> vertexes;
    private final List<Path> edges;

    public Graph(List<Station> vertexes, List<Path> edges) {
        this.vertexes = vertexes;
        this.edges = edges;
    }

    public List<Station> getVertexes() {
        return vertexes;
    }

    public List<Path> getEdges() {
        return edges;
    }
}
