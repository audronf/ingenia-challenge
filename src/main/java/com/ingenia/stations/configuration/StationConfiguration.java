package com.ingenia.stations.configuration;

import com.ingenia.stations.models.Station;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class StationConfiguration {

    private final ArrayList<Station> stations = new ArrayList<>();

    public ArrayList<Station> getStations() {
        return stations;
    }

    public boolean addStation(Station station) {
        return stations.add(station);
    }

    public Optional<Station> findStationById(Long id) {
        return stations.stream().filter(it -> it.getId() == id).findFirst();
    }
}
