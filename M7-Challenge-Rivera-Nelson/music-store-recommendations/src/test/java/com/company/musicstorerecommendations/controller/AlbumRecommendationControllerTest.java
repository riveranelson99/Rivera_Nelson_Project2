package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.AlbumRecommendation;
import com.company.musicstorerecommendations.repository.AlbumRecommendationRepo;
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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AlbumRecommendationController.class)
public class AlbumRecommendationControllerTest {

    @MockBean
    AlbumRecommendationRepo repo;

    private AlbumRecommendation arInput;
    private AlbumRecommendation arOutput;
    private String inputArString;
    private String outputArString;
    private List<AlbumRecommendation> allAr;
    private String allArString;
    private long arId = 10;
    private long wrongArId = 20;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        arInput = new AlbumRecommendation();
        arInput.setAlbumId(1);
        arInput.setUserId(2);
        arInput.setLiked(true);

        arOutput = new AlbumRecommendation();
        arOutput.setAlbumRecommendationId(arId);
        arOutput.setAlbumId(1);
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
    public void shouldCreateAlbumRecommendation() throws Exception {
        mockMvc.perform(post("/albumRecommendation")
                .content(inputArString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputArString));
    }

    @Test
    public void shouldGetAllAlbumRecommendations() throws Exception {
        mockMvc.perform(get("/albumRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allArString));
    }

    @Test
    public void shouldGetOneAlbumRecommendation() throws Exception {
        mockMvc.perform(get("/albumRecommendation/" + arId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputArString));
    }

    @Test
    public void shouldFailToGetOneAlbumRecommendationWithBadId() throws Exception {
        mockMvc.perform(get("/albumRecommendation/" + wrongArId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateAlbumRecommendation() throws Exception {
        mockMvc.perform(put("/albumRecommendation")
                .content(outputArString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAlbumRecommendation() throws Exception {
        mockMvc.perform(delete("/albumRecommendation/" + arId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}