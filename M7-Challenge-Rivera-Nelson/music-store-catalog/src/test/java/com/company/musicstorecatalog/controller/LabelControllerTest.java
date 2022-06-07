package com.company.musicstorecatalog.controller;

import com.company.musicstorecatalog.service.MusicStoreCatalogService;
import com.company.musicstorecatalog.viewModel.LabelViewModel;
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
@WebMvcTest(LabelController.class)
public class LabelControllerTest {

    @MockBean
    MusicStoreCatalogService service;

    private LabelViewModel lvmInput;
    private LabelViewModel lvmOutput;
    private String inputLvmString;
    private String outputLvmString;
    private List<LabelViewModel> allLvm;
    private String allLvmString;
    private long labelId = 10;
    private long wrongLabelId = 20;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        lvmInput = new LabelViewModel();
        lvmInput.setName("Hollywood Records");
        lvmInput.setWebsite("www.hollywoodrecords.com");

        lvmOutput = new LabelViewModel();
        lvmOutput.setId(labelId);
        lvmOutput.setName("Hollywood Records");
        lvmOutput.setWebsite("www.hollywoodrecords.com");

        inputLvmString = mapper.writeValueAsString(lvmInput);
        outputLvmString = mapper.writeValueAsString(lvmOutput);
        allLvm = Arrays.asList(lvmOutput);
        allLvmString = mapper.writeValueAsString(allLvm);

        when(service.createLabel(lvmInput)).thenReturn(lvmOutput);
        when(service.getAllLabels()).thenReturn(allLvm);
        when(service.getLabel(labelId)).thenReturn(lvmOutput);
    }

    @Test
    public void shouldCreateALabel() throws Exception {
        mockMvc.perform(post("/label")
                .content(inputLvmString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(content().json(outputLvmString));
    }

    @Test
    public void shouldGetAllLabels() throws Exception {
        mockMvc.perform(get("/label"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(allLvmString));
    }

    @Test
    public void shouldGetALabelById() throws Exception {
        mockMvc.perform(get("/label/" + labelId))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json(outputLvmString));
    }

    @Test
    public void shouldFailToGetALabelWithBadId() throws Exception {
        mockMvc.perform(get("/label/" + wrongLabelId))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void shouldUpdateALabel() throws Exception {
        mockMvc.perform(put("/label")
                .content(outputLvmString)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void shouldDeleteALabel() throws Exception {
        mockMvc.perform(delete("/label/" + labelId))
                .andDo(print())
                .andExpect(status().isNoContent());
    }
}