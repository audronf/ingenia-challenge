package com.ingenia.stations.controllers;

import com.ingenia.stations.configuration.PathConfiguration;
import com.ingenia.stations.configuration.StationConfiguration;
import com.ingenia.stations.dtos.OptimalPathDto;
import com.ingenia.stations.dtos.PathDto;
import com.ingenia.stations.helpers.DijkstraHelper;
import com.ingenia.stations.helpers.Graph;
import com.ingenia.stations.models.Path;
import com.ingenia.stations.models.Station;
import com.ingenia.stations.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping(value = Constants.PATHS_URL)
public class PathController {

    @Autowired
    private StationConfiguration stationConfiguration;

    @Autowired
    private PathConfiguration pathConfiguration;

    @PostMapping
    public ResponseEntity<Path> create(@RequestBody PathDto pathDto) {
        Optional<Station> source = stationConfiguration.findStationById(pathDto.sourceId);
        Optional<Station> destination = stationConfiguration.findStationById(pathDto.destinationId);

        if (source.isPresent() && destination.isPresent()) {
            Path path = new Path(pathDto.pathId, source.get(), destination.get(), pathDto.cost);
            pathConfiguration.addPath(path);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
            // TODO: error handling
        }
    }

    @GetMapping("/{sourceId}/{destinationId}")
    public ResponseEntity<OptimalPathDto> getOptimalPath(@PathVariable Long sourceId, @PathVariable Long destinationId) {
        Optional<Station> source = stationConfiguration.findStationById(sourceId);
        Optional<Station> destination = stationConfiguration.findStationById(destinationId);
        ArrayList<Station> stations = stationConfiguration.getStations();
        ArrayList<Path> paths = pathConfiguration.getPaths();

        if (source.isPresent() || destination.isPresent()) {
            Graph graph = new Graph(stations, paths);
            DijkstraHelper dijkstraHelper = new DijkstraHelper(graph);
            dijkstraHelper.execute(source.get(), destination.get());
            LinkedList<Station> optimalPath = dijkstraHelper.getPath(destination.get());
            Double totalCost = dijkstraHelper.getTotalCost();
            OptimalPathDto responseBody = new OptimalPathDto(optimalPath.stream().toList(), totalCost);
            return ResponseEntity.ok(responseBody);
        } else {
            return ResponseEntity.badRequest().build();
            // TODO: error handling
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<Path>> getAll() {
        return ResponseEntity.ok(pathConfiguration.getPaths());
    }
}
