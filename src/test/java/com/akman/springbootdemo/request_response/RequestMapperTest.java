package com.akman.springbootdemo.request_response;

import com.akman.springbootdemo.model.enums.Currency;
import com.akman.springbootdemo.model.footbalclub.FootballClub;
import com.akman.springbootdemo.model.footbalclub.FootballClubRequest;
import com.akman.springbootdemo.model.footballer.Footballer;
import com.akman.springbootdemo.model.footballer.FootballerRequest;
import com.akman.springbootdemo.model.footballer.FootballerToSaveRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class RequestMapperTest {

    @Test
    public void testConvertFootballClubRequestToFootballClub() {
        FootballerToSaveRequest footballerToSaveRequest = testCreateFootballerToSaveRequest();

        Footballer footballer = RequestMapper.MAPPER.convertFootballerToSaveRequestToFootballer(footballerToSaveRequest);

        assertEquals(footballer.getId(), footballerToSaveRequest.getId());
        assertEquals(footballer.getFootballClub().getClubName(), footballerToSaveRequest.getFootballClub());
        assertEquals(footballer.getAge(), footballerToSaveRequest.getAge());
        assertEquals(footballer.getCareerStartDate(), footballerToSaveRequest.getCareerStartDate());
        assertEquals(footballer.getContractBeginDate(), footballerToSaveRequest.getContractBeginDate());
        assertEquals(footballer.getContractEndDate(), footballerToSaveRequest.getContractEndDate());
        assertEquals(footballer.getFirstName(), footballerToSaveRequest.getFirstName());
        assertEquals(footballer.getLastName(), footballerToSaveRequest.getLastName());
    }

    /**
     * includes tests: convertFootballerRequestToFootballer
     */
    @Test
    public void testConvertFootballerRequestListToFootballerList() {
        FootballerRequest footballerRequest = testCreateFootballerRequest();
        List<FootballerRequest> footballerRequestList = Collections.singletonList(footballerRequest);

        List<Footballer> footballerList = RequestMapper.MAPPER.convertFootballerRequestListToFootballerList(footballerRequestList);
        Footballer footballer = footballerList.get(0);

        assertEquals(footballer.getId(), footballerRequest.getId());
        assertEquals(footballer.getFirstName(), footballerRequest.getFirstName());
        assertEquals(footballer.getLastName(), footballerRequest.getLastName());
    }

    @Test
    public void testConvertFootballerToSaveRequestToFootballer() {
        FootballClubRequest footballClubRequest = testCreateFootballClubRequest();

        FootballClub footballClub = RequestMapper.MAPPER.convertFootballClubRequestToFootballClub(footballClubRequest);

        assertEquals(footballClub.getId(), footballClubRequest.getId());
        assertEquals(footballClub.getCurrency(), footballClubRequest.getCurrency());
        assertEquals(footballClub.getClubManager(), footballClubRequest.getClubManager());
        assertEquals(footballClub.getClubName(), footballClubRequest.getClubName());
    }

    @Test
    public void testToStringFootballer() {
        Footballer.builder()
                .id(1L)
                .firstName("Radamel")
                .lastName("Falcao")
                .build().toString();
    }

    private FootballerToSaveRequest testCreateFootballerToSaveRequest() {
        return FootballerToSaveRequest.builder()
                .id(1L)
                .age(30)
                .careerStartDate(LocalDate.of(2010, 7, 1))
                .contractBeginDate(LocalDate.of(2020, 7, 1))
                .contractEndDate(LocalDate.of(2023, 7, 1))
                .firstName("Radamel")
                .lastName("Falcao")
                .footballClub("Galatasaray SK")
                .build();
    }

    private FootballerRequest testCreateFootballerRequest() {
        return FootballerRequest.builder()
                .id(1L)
                .firstName("Radamel")
                .lastName("Falcao")
                .build();
    }

    private FootballClubRequest testCreateFootballClubRequest() {
        return FootballClubRequest.builder()
                .id(1L)
                .currency(Currency.TL)
                .clubManager("Fatih Terim")
                .clubName("Galatasaray")
                .build();
    }
}