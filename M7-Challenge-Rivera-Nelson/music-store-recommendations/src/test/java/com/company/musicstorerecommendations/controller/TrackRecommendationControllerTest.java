package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.TrackRecommendation;
import com.company.musicstorerecommendations.repository.TrackRecommendationRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackRecommendationController.class)
public class TrackRecommendationControllerTest {

    @MockBean
    TrackRecommendationRepo repo;

    private TrackRecommendation traInput;
    private TrackRecommendation traOutput;
    private String inputTraString;
    private String outputTraString;
    private List<TrackRecommendation> allTra;
    private String allTraString;
    private long traId = 10;
    private long wrongTraId = 20;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        traInput = new TrackRecommendation();
        traInput.setTrackId(1);
        traInput.setUserId(2);
        traInput.setLiked(true);

        traOutput = new TrackRecommendation();
        traOutput.setTrackRecommendationId(traId);
        traOutput.setTrackId(1);
        traOutput.setUserId(2);
        traOutput.setLiked(true);

        inputTraString = mapper.writeValueAsString(traInput);
        outputTraString = mapper.writeValueAsString(traOutput);
        allTra = Arrays.asList(traOutput);
        allTraString = mapper.writeValueAsString(allTra);

        doReturn(traOutput).when(repo).save(traInput);
        doReturn(allTra).when(repo).findAll();
        doReturn(Optional.of(traOutput)).when(repo).findById(traId);
    }

    @Test
    public void shouldCreateTrackRecommendation() throws Exception {
        mockMvc.perform(post("/trackRecommendation")
                        .content(inputTraString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTraString));
    }

    @Test
    public void shouldGetAllTrackRecommendations() throws Exception {
        mockMvc.perform(get("/trackRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allTraString));
    }

    @Test
    public void shouldGetOneTrackRecommendation() throws Exception {
        mockMvc.perform(get("/trackRecommendation/" + traId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputTraString));
    }

    @Test
    public void shouldFailToGetOneTrackRecommendationWithBadId() throws Exception {
        mockMvc.perform(get("/trackRecommendation/" + wrongTraId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateTrackRecommendation() throws Exception {
        mockMvc.perform(put("/trackRecommendation")
                        .content(outputTraString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteTrackRecommendation() throws Exception {
        mockMvc.perform(delete("/trackRecommendation/" + traId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}