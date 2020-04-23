package com.akman.springbootdemo.rest_api.controller;

import com.akman.springbootdemo.model.footbalclub.FootballClubNameRequest;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import com.akman.springbootdemo.model.footballer.FootballerToSaveRequest;
import com.akman.springbootdemo.rest_api.SpringBootDemoApplication;
import com.akman.springbootdemo.service.footballerservice.FootballerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = SpringBootDemoApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@WebMvcTest(FootballerControllerTest.class)
@RunWith(PowerMockRunner.class)
public class FootballerControllerTest {

    private static final String FOOTBALLER_ENDPOINT = "/api/v1/footballer/";

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Mock
    private FootballerService footballerService;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders
                .standaloneSetup(new FootballerController(footballerService))
                .build();
    }

    @Test
    public void testListFootballers() throws Exception {
        given(footballerService.listFootballers())
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        mvc.perform(get(FOOTBALLER_ENDPOINT + "listFootballers")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testListFootballersByClubName() throws Exception {
        given(footballerService.listFootballersByClubName(anyString()))
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        mvc.perform(get(FOOTBALLER_ENDPOINT + "listFootballersByClubName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clubName", "Galatasaray SK"))
                .andExpect(status().isAccepted());
    }


    @Test
    public void testListFootballersByName() throws Exception {
        given(footballerService.listFootballersByName(anyString()))
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        mvc.perform(get(FOOTBALLER_ENDPOINT + "listFootballersByName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("name", "Radamel Falcao"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testListFootballersWithActiveContractsByFootballClubAndDate() throws Exception {
        given(footballerService.listFootballerWithActiveContractsBySelectedFootballClubAndDate(any(), anyString(), anyString()))
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        String requestStr = objectMapper.writeValueAsString(Collections.singletonList(testCreateFootballClubNameRequest()));

        mvc.perform(post(FOOTBALLER_ENDPOINT + "listFootballerWithActiveContractsByFootballClubAndDate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStr))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testSaveFootballer() throws Exception {
        given(footballerService.saveFootballer(any())).willReturn(testCreateFootballerResponse());

        String requestStr = objectMapper.writeValueAsString(testCreateFootballerToSaveRequest());

        mvc.perform(post(FOOTBALLER_ENDPOINT + "saveFootballer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStr))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSaveFootballer_unprocessableEntity() throws Exception {
        given(footballerService.saveFootballer(any())).willReturn(null);

        String requestStr = objectMapper.writeValueAsString(testCreateFootballerToSaveRequest());

        mvc.perform(post(FOOTBALLER_ENDPOINT + "saveFootballer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStr))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testRemoveAllFootballerByClubName() throws Exception {
        given(footballerService.removeAllFootballerByClubName(anyString()))
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        mvc.perform(get(FOOTBALLER_ENDPOINT + "removeAllFootballerByClubName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clubName", "Galatasaray SK"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testRemoveAllFootballerByClubName_unprocessableEntity() throws Exception {
        given(footballerService.removeAllFootballerByClubName(anyString())).willReturn(null);

        mvc.perform(get(FOOTBALLER_ENDPOINT + "removeAllFootballerByClubName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clubName", "Galatasaray SK"))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void TestRemoveFootballerByName() throws Exception {
        given(footballerService.removeFootballerByName(anyString(), anyString()))
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        mvc.perform(get(FOOTBALLER_ENDPOINT + "removeFootballerByName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clubName", "Galatasaray SK"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testRemoveFootballerByName_unprocessableEntity() throws Exception {
        given(footballerService.removeFootballerByName(anyString(), anyString())).willReturn(null);

        mvc.perform(get(FOOTBALLER_ENDPOINT + "removeFootballerByName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clubName", "Galatasaray SK"))
                .andExpect(status().isUnprocessableEntity());
    }

    private FootballerToSaveRequest testCreateFootballerToSaveRequest() {
        return FootballerToSaveRequest.builder()
                .id(1L)
                .firstName("Radamel")
                .lastName("Falcao")
                .footballClub("Galatasaray SK")
                .build();
    }

    private FootballClubNameRequest testCreateFootballClubNameRequest() {
        return FootballClubNameRequest.builder()
                .id(1L)
                .clubName("Galatasaray SK")
                .build();
    }

    private FootballerResponse testCreateFootballerResponse() {
        return FootballerResponse.builder()
                .id(1L)
                .firstName("Radamel")
                .lastName("Falcao")
                .build();
    }
}
