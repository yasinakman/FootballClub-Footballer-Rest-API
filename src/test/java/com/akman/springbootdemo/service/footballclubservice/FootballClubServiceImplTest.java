package com.akman.springbootdemo.service.footballclubservice;

import com.akman.springbootdemo.model.footbalclub.FootballClub;
import com.akman.springbootdemo.model.footbalclub.FootballClubRequest;
import com.akman.springbootdemo.model.footbalclub.FootballClubResponse;
import com.akman.springbootdemo.model.footballer.FootballerRequest;
import com.akman.springbootdemo.repository.FootballClubRepository;
import com.akman.springbootdemo.service.footballerservice.FootballerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Collections;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FootballClubServiceImpl.class)
public class FootballClubServiceImplTest {

    @InjectMocks
    private FootballClubServiceImpl footballClubService;

    @Mock
    private FootballClubRepository footballClubRepository;

    @Mock
    private FootballerService footballerService;

    @Test
    public void testListFootballClubs() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballClub())).when(footballClubRepository).findAll();

        //test
        footballClubService.listFootballClubs();

        //verification
        verify(footballClubRepository, times(1)).findAll();
    }

    @Test
    public void testListFootballClubsByClubName() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballClub())).when(footballClubRepository).findAllByClubName("Galatasaray SK");

        //test
        footballClubService.listFootballClubsByClubName("Galatasaray SK");

        //verification
        verify(footballClubRepository, times(1)).findAllByClubName("Galatasaray SK");
    }

    @Test
    public void listFootballClubsBySelectedFootballers() {
        //preparation
        doReturn(Collections.singleton(testCreateFootballClub())).when(footballClubRepository).findAllByFootballers_IdIn(any());

        //test
        footballClubService.listFootballClubsBySelectedFootballers(Collections.singletonList(testCreateFootballerRequest()));

        //verification
        verify(footballClubRepository, times(1)).findAllByFootballers_IdIn(any());
    }

    @Test
    public void testSaveFootballClub() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballClub())).when(footballClubRepository).save(any());

        //test
        footballClubService.saveFootballClub(testCreateFootballClubRequest());

        //verification
        verify(footballClubRepository, times(1)).save(any());
    }

    @Test
    public void testSaveFootballClub_exception() {
        //preparation
        doThrow(new NullPointerException()).when(footballClubRepository).save(any());

        //test
        FootballClubResponse result = footballClubService.saveFootballClub(testCreateFootballClubRequest());

        //verification
        verify(footballClubRepository, times(1)).save(any());
        assertNull(result);
    }

    @Test
    public void testRemoveFootballClubByClubName() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballerResponse()))
                .when(footballerService).removeAllFootballerByClubName(anyString());
        doReturn(Collections.singletonList(testCreateFootballClub()))
                .when(footballClubRepository).removeByClubName(anyString());

        //test
        footballClubService.removeFootballClubByClubName("Galatasaray SK");

        //verification
        verify(footballerService, times(1)).removeAllFootballerByClubName(anyString());
        verify(footballClubRepository, times(1)).removeByClubName(anyString());
    }

    private FootballClubResponse testCreateFootballerResponse() {
        return FootballClubResponse.builder()
                .id(1L)
                .clubName("Galatasaray SK")
                .build();
    }

    private FootballClubRequest testCreateFootballClubRequest() {
        return FootballClubRequest.builder()
                .id(1L)
                .clubName("Galatasaray SK")
                .build();
    }

    private FootballerRequest testCreateFootballerRequest() {
        return FootballerRequest.builder()
                .id(1L)
                .firstName("Radamel")
                .lastName("Falcao")
                .build();
    }

    private FootballClub testCreateFootballClub() {
        return FootballClub.builder()
                .id(1L)
                .clubName("Galatasaray SK")
                .build();
    }
}
