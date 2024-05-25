package com.ingenia.stations.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ingenia.stations.configuration.PathConfiguration;
import com.ingenia.stations.configuration.StationConfiguration;
import com.ingenia.stations.constants.TestConstants;
import com.ingenia.stations.dtos.PathDto;
import com.ingenia.stations.dtos.StationDto;
import com.ingenia.stations.utils.Constants;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@WebMvcTest
@Import({StationConfiguration.class, PathConfiguration.class})
public class PathControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void validPath() throws Exception {
        PathDto pathDto = new PathDto(1L, 1L, 2L, 2);
        String requestBody = objectMapper.writeValueAsString(pathDto);

        StationDto stationDto1 = new StationDto(1L, "Facultad de Derecho");
        StationDto stationDto2 = new StationDto(2L, "Las Heras");
        String stationRequestBody1 = objectMapper.writeValueAsString(stationDto1);
        String stationRequestBody2 = objectMapper.writeValueAsString(stationDto2);

        mockMvc.perform(post(TestConstants.STATIONS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stationRequestBody1))
                .andExpect(status().isOk());

        mockMvc.perform(post(TestConstants.STATIONS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(stationRequestBody2))
                .andExpect(status().isOk());

        MvcResult mvcResult = mockMvc.perform(post(TestConstants.PATHS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andReturn();


        assertEquals(200, mvcResult.getResponse().getStatus());
    }

    @Test
    public void invalidCost() throws Exception {
        PathDto pathDto = new PathDto(1L, 1L, 2L, -1);
        String requestBody = objectMapper.writeValueAsString(pathDto);

        mockMvc.perform(post(TestConstants.PATHS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(Constants.INVALID_COST));
    }

    @Test
    public void invalidPath_sameSourceAndDestination() throws Exception {
        // Given
        PathDto pathDto = new PathDto(1L, 1L, 1L, 2);
        String requestBody = objectMapper.writeValueAsString(pathDto);

        // When
        mockMvc.perform(post(TestConstants.PATHS_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value(Constants.INVALID_PATH));
    }
}
