package com.ingenia.stations.helpers;

import com.ingenia.stations.models.Path;
import com.ingenia.stations.models.Station;

import java.util.*;

public class DijkstraHelper {

    /**
     * List of nodes in the graph.
     */
    private final List<Station> nodes;

    /**
     * List of edges in the graph.
     */
    private final List<Path> edges;

    /**
     * Set of nodes whose shortest paths have been determined.
     */
    private Set<Station> settledNodes;

    /**
     * Set of nodes whose shortest paths are yet to be determined.
     */
    private Set<Station> unSettledNodes;

    /**
     * Map of predecessors for each node in the shortest path.
     */
    private Map<Station, Station> predecessors;

    /**
     * Map of shortest distances from the source node to each node.
     */
    private Map<Station, Double> distance;

    private double totalCost;

    /**
     * Constructs a DijkstraHelper object with the given graph.
     *
     * @param graph The graph on which Dijkstra's algorithm will be performed.
     */
    public DijkstraHelper(Graph graph) {
        this.nodes = new ArrayList<Station>(graph.getVertexes());
        this.edges = new ArrayList<Path>(graph.getEdges());
        this.totalCost = 0.0;
    }

    /**
     * Executes Dijkstra's algorithm starting from the given source node.
     *
     * @param source The source node for finding the shortest paths.
     */
    public void execute(Station source, Station target) {
        settledNodes = new HashSet<Station>();
        unSettledNodes = new HashSet<Station>();
        distance = new HashMap<Station, Double>();
        predecessors = new HashMap<Station, Station>();
        totalCost = 0.0;
        distance.put(source, 0D);
        unSettledNodes.add(source);
        while (!unSettledNodes.isEmpty()) {
            Station node = getMinimum(unSettledNodes);
            settledNodes.add(node);
            unSettledNodes.remove(node);
            findMinimalDistances(node);
        }
        calculateTotalCost(target);
    }

    /**
     * Finds minimal distances from the given node to its adjacent nodes.
     *
     * @param node The node for which minimal distances are to be found.
     */

    private void findMinimalDistances(Station node) {
        List<Station> adjacentNodes = getNeighbors(node);
        for (Station target : adjacentNodes) {
            double newDistance = getShortestDistance(node) + getDistance(node, target);
            if (newDistance < getShortestDistance(target)) {
                distance.put(target, newDistance);
                predecessors.put(target, node);
                if (!isSettled(target)) {
                    unSettledNodes.remove(target);
                    unSettledNodes.add(target);
                }
            }
        }
    }

    /**
     * Calculates the distance between two nodes in the graph.
     *
     * @param node The origin node.
     * @param target The destination node.
     * @return The distance between the two nodes.
     */
    private double getDistance(Station node, Station target) {
        for (Path edge : edges) {
            if (edge.getOrigin().equals(node)
                    && edge.getDestination().equals(target)) {
                return edge.getCost();
            }
        }
        throw new RuntimeException("Should not happen");
    }

    /**
     * Retrieves the neighbors of the given node.
     *
     * @param node The node for which neighbors are to be retrieved.
     * @return The list of neighbor nodes.
     */
    private List<Station> getNeighbors(Station node) {
        List<Station> neighbors = new ArrayList<Station>();
        for (Path edge : edges) {
            if (edge.getOrigin().equals(node)
                    && !isSettled(edge.getDestination())) {
                neighbors.add(edge.getDestination());
            }
        }
        return neighbors;
    }

    /**
     * Finds the node with the minimum distance among a set of nodes.
     *
     * @param vertexes The set of nodes.
     * @return The node with the minimum distance.
     */
    private Station getMinimum(Set<Station> vertexes) {
        Station minimum = null;
        for (Station vertex : vertexes) {
            if (minimum == null) {
                minimum = vertex;
            } else {
                if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
                    minimum = vertex;
                }
            }
        }
        return minimum;
    }

    /**
     * Checks if a node is settled (i.e., its shortest distance has been determined).
     *
     * @param vertex The node to be checked.
     * @return True if the node is settled, false otherwise.
     */
    private boolean isSettled(Station vertex) {
        return settledNodes.contains(vertex);
    }

    /**
     * Retrieves the shortest distance from the source node to the given destination node.
     *
     * @param destination The destination node.
     * @return The shortest distance to the destination node.
     */
    private Double getShortestDistance(Station destination) {
        Double d = distance.get(destination);
        if (d == null) {
            return Double.POSITIVE_INFINITY;
        } else {
            return d;
        }
    }

    private void calculateTotalCost(Station target) {
        this.totalCost = getShortestDistance(target);
    }

    /**
     * Retrieves the shortest path from the source node to the target node.
     *
     * @param target The target node.
     * @return The shortest path from the source to the target node.
     */
    public LinkedList<Station> getPath(Station target) {
        LinkedList<Station> path = new LinkedList<Station>();
        Station step = target;
        if (predecessors.get(step) == null) {
            return null;
        }
        path.add(step);
        while (predecessors.get(step) != null) {
            step = predecessors.get(step);
            path.add(step);
        }
        Collections.reverse(path);
        return path;
    }

    /**
     * Retrieves the total cost of the optimal path.
     *
     * @return The total cost of the path.
     */
    public double getTotalCost() {
        return totalCost;
    }

}