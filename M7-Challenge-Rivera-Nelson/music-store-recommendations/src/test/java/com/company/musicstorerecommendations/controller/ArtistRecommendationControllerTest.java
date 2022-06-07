package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.ArtistRecommendation;
import com.company.musicstorerecommendations.repository.ArtistRecommendationRepo;
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

import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistRecommendationController.class)
public class ArtistRecommendationControllerTest {

    @MockBean
    ArtistRecommendationRepo repo;

    private ArtistRecommendation arInput;
    private ArtistRecommendation arOutput;
    private String inputArString;
    private String outputArString;
    private List<ArtistRecommendation> allAr;
    private String allArString;
    private long arId = 10;
    private long wrongArId = 20;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        arInput = new ArtistRecommendation();
        arInput.setArtistId(1);
        arInput.setUserId(2);
        arInput.setLiked(true);

        arOutput = new ArtistRecommendation();
        arOutput.setArtistRecommendationId(arId);
        arOutput.setArtistId(1);
        arOutput.setUserId(2);
        arOutput.setLiked(true);

        inputArString = mapper.writeValueAsString(arInput);
        outputArString = mapper.writeValueAsString(arOutput);
        allAr = Arrays.asList(arOutput);
        allArString = mapper.writeValueAsString(allAr);

        doReturn(arOutput).when(repo).save(arInput);
        doReturn(allAr).when(repo).findAll();
        doReturn(Optional.of(arOutput)).when(repo).findById(arId);
    }

    @Test
    public void shouldCreateArtistRecommendation() throws Exception {
        mockMvc.perform(post("/artistRecommendation")
                        .content(inputArString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArString));
    }

    @Test
    public void shouldGetAllArtistRecommendations() throws Exception {
        mockMvc.perform(get("/artistRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allArString));
    }

    @Test
    public void shouldGetOneArtistRecommendation() throws Exception {
        mockMvc.perform(get("/artistRecommendation/" + arId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputArString));
    }

    @Test
    public void shouldFailToGetOneArtistRecommendationWithBadId() throws Exception {
        mockMvc.perform(get("/artistRecommendation/" + wrongArId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateArtistRecommendation() throws Exception {
        mockMvc.perform(put("/artistRecommendation")
                        .content(outputArString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteArtistRecommendation() throws Exception {
        mockMvc.perform(delete("/artistRecommendation/" + arId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}