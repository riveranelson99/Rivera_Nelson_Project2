package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.service.MusicStoreCatalogService;
import com.company.musicstorecatalog.viewModel.ArtistViewModel;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ArtistController.class)
public class ArtistControllerTest {

    @MockBean
    MusicStoreCatalogService service;

    private ArtistViewModel avmInput;
    private ArtistViewModel avmOutput;
    private String inputAvmString;
    private String outputAvmString;
    private List<ArtistViewModel> allAvm;
    private String allAvmString;
    private long artistId = 10;
    private long wrongArtistId = 20;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        avmInput = new ArtistViewModel();
        avmInput.setName("Queen");
        avmInput.setInstagram("@officialqueenmusic");
        avmInput.setTwitter("@QueenWillRock");

        avmOutput = new ArtistViewModel();
        avmOutput.setId(artistId);
        avmOutput.setName("Queen");
        avmOutput.setInstagram("@officialqueenmusic");
        avmOutput.setTwitter("@QueenWillRock");

        inputAvmString = mapper.writeValueAsString(avmInput);
        outputAvmString = mapper.writeValueAsString(avmOutput);
        allAvm = Arrays.asList(avmOutput);
        allAvmString = mapper.writeValueAsString(allAvm);

        when(service.createArtist(avmInput)).thenReturn(avmOutput);
        when(service.getAllArtists()).thenReturn(allAvm);
        when(service.getArtist(artistId)).thenReturn(avmOutput);
    }

    @Test
    public void shouldCreatAnArtist() throws Exception {
        mockMvc.perform(post("/artist")
                .content(inputAvmString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputAvmString));
    }

    @Test
    public void shouldGetAllArtists() throws Exception {
        mockMvc.perform(get("/artist"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allAvmString));
    }

    @Test
    public void shouldGetAnArtistById() throws Exception {
        mockMvc.perform(get("/artist/" + artistId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputAvmString));
    }

    @Test
    public void shouldFailToGetAnArtistWithBadId() throws Exception {
        mockMvc.perform(get("/artist/" + wrongArtistId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateAnArtist() throws Exception {
        mockMvc.perform(put("/artist")
                .content(outputAvmString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAnArtist() throws Exception {
        mockMvc.perform(delete("/artist/" +artistId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}