package com.akman.springbootdemo.rest_api.controller;

import com.akman.springbootdemo.model.enums.Currency;
import com.akman.springbootdemo.model.footbalclub.FootballClubRequest;
import com.akman.springbootdemo.model.footbalclub.FootballClubResponse;
import com.akman.springbootdemo.model.footballer.FootballerRequest;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import com.akman.springbootdemo.service.footballclubservice.FootballClubService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.modules.junit4.PowerMockRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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

@WebMvcTest(FootballClubController.class)
@RunWith(PowerMockRunner.class)
public class FootballClubControllerTest {

    private static final String FOOTBALL_CLUB_ENDPOINT = "/api/v1/footballClub/";

    private MockMvc mvc;

    private ObjectMapper objectMapper;

    @Mock
    private FootballClubService footballClubService;

    @Before
    public void setUp() {
        objectMapper = new ObjectMapper();
        mvc = MockMvcBuilders
                .standaloneSetup(new FootballClubController(footballClubService))
                .build();
    }

    @Test
    public void testListFootballClubs() throws Exception {
        given(footballClubService.listFootballClubs())
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        mvc.perform(get(FOOTBALL_CLUB_ENDPOINT + "listFootballClubs")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testListFootballClubsByClubName() throws Exception {
        given(footballClubService.listFootballClubsByClubName(anyString()))
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        mvc.perform(get(FOOTBALL_CLUB_ENDPOINT + "listFootballClubsByClubName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clubName", "Galatasaray SK"))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testListFootballClubsByFootballers() throws Exception {
        given(footballClubService.listFootballClubsBySelectedFootballers(any()))
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        String requestStr = objectMapper.writeValueAsString(Collections.singletonList(testCreateFootballerRequest()));

        mvc.perform(post(FOOTBALL_CLUB_ENDPOINT + "listFootballClubsByFootballers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStr))
                .andExpect(status().isAccepted());
    }

    @Test
    public void testSaveFootballClub() throws Exception {
        given(footballClubService.saveFootballClub(any()))
                .willReturn(testCreateFootballerResponse());

        String requestStr = objectMapper.writeValueAsString(testCreateFootballClubRequest());

        mvc.perform(post(FOOTBALL_CLUB_ENDPOINT + "saveFootballClub")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStr))
                .andExpect(status().isCreated());
    }

    @Test
    public void testSaveFootballClub_unprocessableEntity() throws Exception {
        given(footballClubService.saveFootballClub(any()))
                .willReturn(null);

        String requestStr = objectMapper.writeValueAsString(testCreateFootballClubRequest());

        mvc.perform(post(FOOTBALL_CLUB_ENDPOINT + "saveFootballClub")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestStr))
                .andExpect(status().isUnprocessableEntity());
    }

    @Test
    public void testRemoveFootballClubByName() throws Exception {
        given(footballClubService.removeFootballClubByClubName(any()))
                .willReturn(Collections.singletonList(testCreateFootballerResponse()));

        mvc.perform(get(FOOTBALL_CLUB_ENDPOINT + "removeFootballClubByName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clubName", "Galatasaray SK"))
                .andExpect(status().isAccepted());
    }


    @Test
    public void testRemoveFootballClubByName_unprocessableEntity() throws Exception {
        given(footballClubService.removeFootballClubByClubName(any()))
                .willReturn(null);

        mvc.perform(get(FOOTBALL_CLUB_ENDPOINT + "removeFootballClubByName")
                .contentType(MediaType.APPLICATION_JSON)
                .param("clubName", "Galatasaray SK"))
                .andExpect(status().isUnprocessableEntity());
    }

    private FootballClubRequest testCreateFootballClubRequest() {
        return FootballClubRequest.builder()
                .id(1L)
                .clubName("Galatasaray SK")
                .clubManager("Fatih Terim")
                .currency(Currency.TL)
                .build();
    }

    private FootballerRequest testCreateFootballerRequest() {
        return FootballerRequest.builder()
                .id(1L)
                .firstName("Radamel")
                .lastName("Falcao")
                .build();
    }

    private FootballClubResponse testCreateFootballerResponse() {
        return FootballClubResponse.builder()
                .id(1L)
                .clubName("Galatasaray SK")
                .clubManager("Fatih Terim")
                .currency(Currency.TL)
                .footballers(Collections.singletonList(FootballerResponse.builder()
                        .id(2L)
                        .firstName("Radamel")
                        .lastName("Falcao")
                        .build()))
                .build();
    }
}
