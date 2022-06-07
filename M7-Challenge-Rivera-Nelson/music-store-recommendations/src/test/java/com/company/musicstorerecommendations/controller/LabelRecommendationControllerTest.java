package com.company.musicstorerecommendations.controller;

import com.company.musicstorerecommendations.model.LabelRecommendation;
import com.company.musicstorerecommendations.repository.LabelRecommendationRepo;
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
@WebMvcTest(LabelRecommendationController.class)
public class LabelRecommendationControllerTest {

    @MockBean
    LabelRecommendationRepo repo;

    private LabelRecommendation labInput;
    private LabelRecommendation labOutput;
    private String inputLabString;
    private String outputLabString;
    private List<LabelRecommendation> allLab;
    private String allLabString;
    private long labId = 10;
    private long wrongLabId = 20;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        labInput = new LabelRecommendation();
        labInput.setLabelId(1);
        labInput.setUserId(2);
        labInput.setLiked(true);

        labOutput = new LabelRecommendation();
        labOutput.setLabelRecommendationId(labId);
        labOutput.setLabelId(1);
        labOutput.setUserId(2);
        labOutput.setLiked(true);

        inputLabString = mapper.writeValueAsString(labInput);
        outputLabString = mapper.writeValueAsString(labOutput);
        allLab = Arrays.asList(labOutput);
        allLabString = mapper.writeValueAsString(allLab);

        doReturn(labOutput).when(repo).save(labInput);
        doReturn(allLab).when(repo).findAll();
        doReturn(Optional.of(labOutput)).when(repo).findById(labId);
    }

    @Test
    public void shouldCreateLabelRecommendation() throws Exception {
        mockMvc.perform(post("/labelRecommendation")
                        .content(inputLabString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLabString));
    }

    @Test
    public void shouldGetAllLabelRecommendations() throws Exception {
        mockMvc.perform(get("/labelRecommendation"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allLabString));
    }

    @Test
    public void shouldGetOneLabelRecommendation() throws Exception {
        mockMvc.perform(get("/labelRecommendation/" + labId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputLabString));
    }

    @Test
    public void shouldFailToGetOneLabelRecommendationWithBadId() throws Exception {
        mockMvc.perform(get("/labelRecommendation/" + wrongLabId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateLabelRecommendation() throws Exception {
        mockMvc.perform(put("/labelRecommendation")
                        .content(outputLabString)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteLabelRecommendation() throws Exception {
        mockMvc.perform(delete("/labelRecommendation/" + labId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}