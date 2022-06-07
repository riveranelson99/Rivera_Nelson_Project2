package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.models.Album;
import com.company.musicstorecatalog.service.MusicStoreCatalogService;
import com.company.musicstorecatalog.viewModel.TrackViewModel;
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

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(TrackController.class)
public class TrackControllerTest {

    @MockBean
    MusicStoreCatalogService service;

    private TrackViewModel tvmInput;
    private TrackViewModel tvmOutput;
    private String inputTvmString;
    private String outputTvmString;
    private List<TrackViewModel> allTvm;
    private String allTvmString;
    private long trackId = 10;
    private long wrongTrackId = 20;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        Album inputAlbum = new Album();
        inputAlbum.setAlbumId(2);
        inputAlbum.setTitle("Jazz");
        inputAlbum.setArtistId(3);
        inputAlbum.setReleaseDate(LocalDate.of(1978, 11, 10));
        inputAlbum.setLabelId(4);
        inputAlbum.setListPrice(new BigDecimal("23.99"));

        tvmInput = new TrackViewModel();
        tvmInput.setAlbum(inputAlbum);
        tvmInput.setTitle("Don't Stop Me Now.");
        tvmInput.setRunTime(4);

        tvmOutput = new TrackViewModel();
        tvmOutput.setId(trackId);
        tvmOutput.setAlbum(inputAlbum);
        tvmOutput.setTitle("Don't Stop Me Now.");
        tvmOutput.setRunTime(4);

        inputTvmString = mapper.writeValueAsString(tvmInput);
        outputTvmString = mapper.writeValueAsString(tvmOutput);
        allTvm = Arrays.asList(tvmOutput);
        allTvmString = mapper.writeValueAsString(allTvm);

        when(service.createTrack(tvmInput)).thenReturn(tvmOutput);
        when(service.getAllTracks()).thenReturn(allTvm);
        when(service.getTrack(trackId)).thenReturn(tvmOutput);
    }

    @Test
    public void shouldCreateATrack() throws Exception {
        mockMvc.perform(post("/track")
                .content(inputTvmString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputTvmString));
    }

    @Test
    public void shouldGetAllTracks() throws Exception {
        mockMvc.perform(get("/track"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allTvmString));
    }

    @Test
    public void shouldGetOneTrack() throws Exception {
        mockMvc.perform(get("/track/" +trackId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputTvmString));
    }

    @Test
    public void shouldFailToGetOneTrackWithBadId() throws Exception {
        mockMvc.perform(get("/track/" + wrongTrackId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateATrack() throws Exception {
        mockMvc.perform(put("/track")
                .content(outputTvmString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteATrack() throws Exception {
        mockMvc.perform(delete("/track/" + trackId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}