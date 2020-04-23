package com.akman.springbootdemo.service.footballerservice;

import com.akman.springbootdemo.model.footbalclub.FootballClub;
import com.akman.springbootdemo.model.footbalclub.FootballClubNameRequest;
import com.akman.springbootdemo.model.footballer.Footballer;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import com.akman.springbootdemo.model.footballer.FootballerToSaveRequest;
import com.akman.springbootdemo.repository.FootballClubRepository;
import com.akman.springbootdemo.repository.FootballerRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.time.LocalDate;
import java.util.Collections;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FootballerServiceImpl.class)
public class FootballerServiceImplTest {

    @InjectMocks
    private FootballerServiceImpl footballerService;

    @Mock
    private FootballClubRepository footballClubRepository;

    @Mock
    private FootballerRepository footballerRepository;

    @Test
    public void testListFootballers() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballer())).when(footballerRepository).findAll();

        //test
        footballerService.listFootballers();

        //verification
        verify(footballerRepository, times(1)).findAll();
    }

    @Test
    public void testListFootballersByClubName() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballer())).when(footballerRepository).findByFootballClub_ClubName("Galatasaray SK");

        //test
        footballerService.listFootballersByClubName("Galatasaray SK");

        //verification
        verify(footballerRepository, times(1)).findByFootballClub_ClubName("Galatasaray SK");
    }

    @Test
    public void testListFootballersByName() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballer())).when(footballerRepository).findByFirstNameAndLastName("Radamel Falcao");

        //test
        footballerService.listFootballersByName("Radamel Falcao");

        //verification
        verify(footballerRepository, times(1)).findByFirstNameAndLastName("Radamel Falcao");
    }

    @Test
    public void testListFootballerWithActiveContractsBySelectedFootballClubAndDate() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballer())).when(footballerRepository)
                .findAllByFootballClub_IdInAndContractBeginDateLessThanEqualAndContractEndDateGreaterThanEqual(any(), any(), any());

        //test
        footballerService.listFootballerWithActiveContractsBySelectedFootballClubAndDate(Collections
                .singletonList(testCreateFootballClubNameRequest()), "2020", "2021");

        //verification
        verify(footballerRepository, times(1))
                .findAllByFootballClub_IdInAndContractBeginDateLessThanEqualAndContractEndDateGreaterThanEqual(any(), any(), any());
    }

    @Test
    public void testSaveFootballer() {
        //preparation
        doReturn(testCreateFootballClub()).when(footballClubRepository).findByClubName(anyString());
        doReturn(Collections.singletonList(testCreateFootballer())).when(footballerRepository).save(any());

        //test
        footballerService.saveFootballer(testCreateFootballerToSaveRequest());

        //verification
        verify(footballClubRepository, times(1)).findByClubName(anyString());
        verify(footballerRepository, times(1)).save(any());
        ;
    }

    @Test
    public void testSaveFootballer_exception() {
        //preparation
        doReturn(testCreateFootballClub()).when(footballClubRepository).findByClubName(anyString());
        doThrow(new NullPointerException()).when(footballerRepository).save(any());

        //test
        FootballerResponse result = footballerService.saveFootballer(testCreateFootballerToSaveRequest());

        //verification
        verify(footballClubRepository, times(1)).findByClubName(anyString());
        verify(footballerRepository, times(1)).save(any());
        assertNull(result);
    }

    @Test
    public void testSaveFootballer_footballClubNotExisting() {
        //preparation
        doReturn(null).when(footballClubRepository).findByClubName(anyString());

        //test
        FootballerResponse result = footballerService.saveFootballer(testCreateFootballerToSaveRequest());

        //verification
        verify(footballClubRepository, times(1)).findByClubName(anyString());
        verify(footballerRepository, never()).save(any());
        assertNull(result);
    }

    @Test
    public void testRemoveAllFootballerByClubName() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballer())).when(footballerRepository)
                .removeAllByFootballClub_ClubName(anyString());

        //test
        footballerService.removeAllFootballerByClubName("Galatasaray SK");

        //verification
        verify(footballerRepository, times(1))
                .removeAllByFootballClub_ClubName(anyString());
    }

    @Test
    public void removeFootballerByName() {
        //preparation
        doReturn(Collections.singletonList(testCreateFootballer())).when(footballerRepository)
                .removeByFirstNameAndLastName(anyString(), anyString());

        //test
        footballerService.removeFootballerByName("Radamel", "Falcao");

        //verification
        verify(footballerRepository, times(1))
                .removeByFirstNameAndLastName(anyString(), anyString());
    }

    private FootballClub testCreateFootballClub() {
        return FootballClub.builder()
                .id(1L)
                .clubName("Galatasaray SK")
                .build();
    }

    private FootballerToSaveRequest testCreateFootballerToSaveRequest() {
        return FootballerToSaveRequest.builder()
                .id(1L)
                .firstName("Radamel")
                .lastName("Falcao")
                .careerStartDate(LocalDate.of(2010, 7, 1))
                .age(30)
                .footballClub("Galatasaray SK")
                .build();
    }

    private FootballClubNameRequest testCreateFootballClubNameRequest() {
        return FootballClubNameRequest.builder()
                .id(1L)
                .clubName("Galatasaray SK")
                .build();
    }

    private Footballer testCreateFootballer() {
        return Footballer.builder()
                .id(1L)
                .firstName("Radamel")
                .lastName("Falcao")
                .build();
    }
}