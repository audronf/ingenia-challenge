package com.ingenia.stations.controllers;

import com.ingenia.stations.configuration.StationConfiguration;
import com.ingenia.stations.dtos.PathDto;
import com.ingenia.stations.models.Path;
import com.ingenia.stations.models.Station;
import com.ingenia.stations.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;

@RestController
@RequestMapping(value = Constants.PATHS_URL)
public class PathController {

    @Autowired
    private StationConfiguration stationConfiguration;

    @PostMapping
    public ResponseEntity<Path> create(@RequestBody PathDto pathDto) {
        Optional<Station> source = stationConfiguration.findStationById(pathDto.sourceId);
        Optional<Station> destination = stationConfiguration.findStationById(pathDto.destinationId);

        if (source.isPresent() && destination.isPresent()) {
            Path path = new Path(pathDto.pathId, source.get(), destination.get(), pathDto.cost);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
            // TODO: error handling
        }
    }
}
