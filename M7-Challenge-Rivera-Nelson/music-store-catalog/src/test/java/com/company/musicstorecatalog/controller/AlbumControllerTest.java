package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.models.Artist;
import com.company.musicstorecatalog.models.Label;
import com.company.musicstorecatalog.service.MusicStoreCatalogService;
import com.company.musicstorecatalog.viewModel.AlbumViewModel;
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
@WebMvcTest(AlbumController.class)
public class AlbumControllerTest {

    @MockBean
    MusicStoreCatalogService service;

    private AlbumViewModel avmInput;
    private AlbumViewModel avmOutput;
    private String avmStringInput;
    private String avmStringOutput;
    private List<AlbumViewModel> allAvm;
    private String allAvmString;
    private long albumId = 10;
    private long wrongAlbumId = 20;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        Artist inputArt = new Artist();
        inputArt.setArtistId(2);
        inputArt.setName("Queen");
        inputArt.setInstagram("@officialqueenmusic");
        inputArt.setTwitter("@QueenWillRock");

        Label inputLab = new Label();
        inputLab.setLabelId(6);
        inputLab.setName("Hollywood Records");
        inputLab.setWebsite("www.hollywoodrecords.com");

        avmInput = new AlbumViewModel();
        avmInput.setTitle("Jazz");
        avmInput.setArtist(inputArt);
        avmInput.setReleaseDate(LocalDate.of(1978, 11, 10));
        avmInput.setLabel(inputLab);
        avmInput.setListPrice(new BigDecimal("23.99"));

        avmOutput = new AlbumViewModel();
        avmOutput.setId(albumId);
        avmOutput.setTitle("Jazz");
        avmOutput.setArtist(inputArt);
        avmOutput.setReleaseDate(LocalDate.of(1978, 11, 10));
        avmOutput.setLabel(inputLab);
        avmOutput.setListPrice(new BigDecimal("23.99"));

        avmStringInput = mapper.writeValueAsString(avmInput);
        avmStringOutput = mapper.writeValueAsString(avmOutput);
        allAvm = Arrays.asList(avmOutput);
        allAvmString = mapper.writeValueAsString(allAvm);

        when(service.createAlbum(avmInput)).thenReturn(avmOutput);
        when(service.getAllAlbums()).thenReturn(allAvm);
        when(service.getAlbum(albumId)).thenReturn(avmOutput);
    }

    @Test
    public void shouldCreateAnAlbum() throws Exception {
        mockMvc.perform(post("/album")
                        .content(avmStringInput)
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(avmStringOutput));
    }

    @Test
    public void shouldGetAllAlbums() throws Exception {
        mockMvc.perform(get("/album"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allAvmString));
    }

    @Test
    public void shouldGetOneAlbumById() throws Exception {
        mockMvc.perform(get("/album/" + albumId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(avmStringOutput));
    }

    @Test
    public void shouldFailToGetAnAlbumWithBadId() throws Exception {
        mockMvc.perform(get("/album/" + wrongAlbumId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateAnAlbum() throws Exception {
        mockMvc.perform(put("/album")
                .content(avmStringOutput)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteAnAlbum() throws Exception {
        mockMvc.perform(delete("/album/" + albumId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}