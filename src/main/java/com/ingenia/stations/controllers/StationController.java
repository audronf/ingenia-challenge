package com.ingenia.stations.controllers;

import com.ingenia.stations.configuration.StationConfiguration;
import com.ingenia.stations.dtos.StationDto;
import com.ingenia.stations.exceptions.StationNameAlreadyExistsException;
import com.ingenia.stations.models.Station;
import com.ingenia.stations.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = Constants.STATIONS_URL)
public class StationController {

    @Autowired
    private StationConfiguration stationConfiguration;

    @PostMapping
    public ResponseEntity<Station> create(@RequestBody StationDto stationDto) throws StationNameAlreadyExistsException {
        Station station = new Station(stationDto.id, stationDto.name);
        if (stationConfiguration.getStations().stream().anyMatch(it -> it.getName().equalsIgnoreCase(stationDto.name))) {
            throw new StationNameAlreadyExistsException(Constants.STATION_ALREADY_EXISTS);
        }
        stationConfiguration.addStation(station);
        return ResponseEntity.ok().build();
    }
}
