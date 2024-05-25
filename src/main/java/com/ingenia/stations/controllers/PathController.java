package com.ingenia.stations.controllers;

import com.ingenia.stations.configuration.PathConfiguration;
import com.ingenia.stations.configuration.StationConfiguration;
import com.ingenia.stations.dtos.OptimalPathDto;
import com.ingenia.stations.dtos.PathDto;
import com.ingenia.stations.exceptions.InvalidCostException;
import com.ingenia.stations.exceptions.InvalidPathException;
import com.ingenia.stations.exceptions.PathNotFoundException;
import com.ingenia.stations.exceptions.StationNotFoundException;
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
    public ResponseEntity<Path> create(@RequestBody PathDto pathDto) throws StationNotFoundException, InvalidCostException, InvalidPathException {
        if (pathDto.cost < 0) {
            throw new InvalidCostException(Constants.INVALID_COST);
        }

        if (pathDto.sourceId == pathDto.destinationId) {
            throw new InvalidPathException(Constants.INVALID_PATH);
        }

        Optional<Station> source = stationConfiguration.findStationById(pathDto.sourceId);
        Optional<Station> destination = stationConfiguration.findStationById(pathDto.destinationId);

        if (source.isPresent() && destination.isPresent()) {
            Path path = new Path(pathDto.pathId, source.get(), destination.get(), pathDto.cost);
            pathConfiguration.addPath(path);
            return ResponseEntity.ok().build();
        } else {
            throw new StationNotFoundException(Constants.STATION_NOT_FOUND);
        }
    }

    @GetMapping("/{sourceId}/{destinationId}")
    public ResponseEntity<OptimalPathDto> getOptimalPath(@PathVariable Long sourceId, @PathVariable Long destinationId)
            throws PathNotFoundException, StationNotFoundException {
        Optional<Station> source = stationConfiguration.findStationById(sourceId);
        Optional<Station> destination = stationConfiguration.findStationById(destinationId);
        ArrayList<Station> stations = stationConfiguration.getStations();
        ArrayList<Path> paths = pathConfiguration.getPaths();

        if (source.isPresent() && destination.isPresent()) {
            Graph graph = new Graph(stations, paths);
            DijkstraHelper dijkstraHelper = new DijkstraHelper(graph);
            dijkstraHelper.execute(source.get(), destination.get());
            LinkedList<Station> optimalPath = dijkstraHelper.getPath(destination.get());
            Double totalCost = dijkstraHelper.getTotalCost();
            if (optimalPath == null) {
                throw new PathNotFoundException(Constants.PATH_NOT_FOUND);
            }
            OptimalPathDto responseBody = new OptimalPathDto(optimalPath.stream().toList(), totalCost);
            return ResponseEntity.ok(responseBody);
        } else {
            throw new StationNotFoundException(Constants.STATION_NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<ArrayList<Path>> getAll() {
        return ResponseEntity.ok(pathConfiguration.getPaths());
    }
}
