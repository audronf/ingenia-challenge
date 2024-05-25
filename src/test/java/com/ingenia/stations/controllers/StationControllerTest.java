package com.ingenia.stations.controllers;

import com.ingenia.stations.configuration.StationConfiguration;
import com.ingenia.stations.dtos.StationDto;
import com.ingenia.stations.exceptions.StationNameAlreadyExistsException;
import com.ingenia.stations.models.Station;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.ResponseEntity;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class StationControllerTest {

    @Mock
    private StationConfiguration stationConfiguration;

    @InjectMocks
    private StationController stationController;

    @BeforeEach
    void setUp() {
        initMocks(this);
    }

    @Test
    void testCreateStation() throws StationNameAlreadyExistsException {
        StationDto stationDto = new StationDto(1L, "Facultad de Derecho");
        when(stationConfiguration.getStations()).thenReturn(new ArrayList<>());

        ResponseEntity<Station> responseEntity;
        responseEntity = stationController.create(stationDto);
        assertNotNull(responseEntity);
        assertEquals(200,
                responseEntity.getStatusCode().value());
        verify(stationConfiguration, times(1)).getStations();
    }

    @Test
    void testCreateStationAlreadyExists() {
        StationDto stationDto = new StationDto(1L, "Facultad de Derecho");
        Station station = new Station(stationDto.getId(), stationDto.getName());
        ArrayList<Station> existingStations = new ArrayList<>();
        existingStations.add(station);

        when(stationConfiguration.getStations()).thenReturn(existingStations);

        assertThrows(StationNameAlreadyExistsException.class, () -> stationController.create(stationDto));
        verify(stationConfiguration, times(1)).getStations();
        verify(stationConfiguration, times(0)).addStation(station);
    }
}
