package com.akman.springbootdemo.request_response;

import com.akman.springbootdemo.model.enums.Currency;
import com.akman.springbootdemo.model.footbalclub.FootballClub;
import com.akman.springbootdemo.model.footbalclub.FootballClubResponse;
import com.akman.springbootdemo.model.footballer.Footballer;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import org.junit.jupiter.api.Test;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ResponseMapperTest {

    /**
     * includes tests: convertFootballClubToFootballClubResponse
     */
    @Test
    public void testConvertFootballClubListToFootballClubResponseList() {
        FootballClub footballClub = testCreateFootballClub();
        List<FootballClub> footballClubList = Collections.singletonList(footballClub);
        FootballClub footballClub_footballerNull = testCreateFootballClub_footballerNull();
        List<FootballClub> footballClubList_footballerNull = Collections.singletonList(footballClub_footballerNull);

        List<FootballClubResponse> footballClubResponseList = ResponseMapper.MAPPER.convertFootballClubListToFootballClubResponseList(footballClubList);
        FootballClubResponse footballClubResponse = footballClubResponseList.get(0);

        List<FootballClubResponse> footballClubResponseList_footballerNull = ResponseMapper.MAPPER.convertFootballClubListToFootballClubResponseList(footballClubList_footballerNull);
        FootballClubResponse footballClubResponse_footballerNull = footballClubResponseList_footballerNull.get(0);

        assertEquals(footballClubResponse.getId(), footballClub.getId());
        assertEquals(footballClubResponse.getClubManager(), footballClub.getClubManager());
        assertEquals(footballClubResponse.getClubName(), footballClub.getClubName());
        assertEquals(footballClubResponse.getCurrency(), footballClub.getCurrency());
        assertEquals(footballClubResponse.getFootballers().get(0).getFirstName(), footballClub.getFootballers().get(0).getFirstName());
        assertEquals(footballClubResponse.getFootballers().get(0).getLastName(), footballClub.getFootballers().get(0).getLastName());
        assertEquals(CollectionUtils.isEmpty(footballClubResponse_footballerNull.getFootballers()), CollectionUtils.isEmpty(footballClub_footballerNull.getFootballers()));
    }

    /**
     * includes tests: convertFootballerToFootballerResponse
     */
    @Test
    public void convertFootballerListToFootballerResponseList() {
        Footballer footballer = testCreateFootballer();
        List<Footballer> footballerList = Collections.singletonList(footballer);

        List<FootballerResponse> footballerResponses = ResponseMapper.MAPPER.convertFootballerListToFootballerResponseList(footballerList);
        FootballerResponse footballerResponse = footballerResponses.get(0);

        assertEquals(footballerResponse.getId(), footballer.getId());
        assertEquals(footballerResponse.getAge(), footballer.getAge());
        assertEquals(footballerResponse.getCurrency(), footballer.getCurrency());
        assertEquals(footballerResponse.getFirstName(), footballer.getFirstName());
        assertEquals(footballerResponse.getLastName(), footballer.getLastName());
        assertEquals(footballerResponse.getTransferFee(), footballer.getTransferFee());
        assertEquals(footballerResponse.getContractFee(), footballer.getContractFee());
        assertEquals(footballerResponse.getCareerStartDate(), footballer.getCareerStartDate());
        assertEquals(footballerResponse.getContractBeginDate(), footballer.getContractBeginDate());
        assertEquals(footballerResponse.getContractEndDate(), footballer.getContractEndDate());
        assertEquals(footballerResponse.getFootballClub(), footballer.getFootballClub().getClubName());
    }

    private FootballClub testCreateFootballClub() {
        return FootballClub.builder()
                .id(1L)
                .currency(Currency.TL.getValue())
                .clubManager("Fatih Terim")
                .clubName("Galatasaray SK")
                .footballers(Collections.singletonList(Footballer.builder()
                        .id(4L)
                        .firstName("Radamel")
                        .lastName("Falcao")
                        .build()))
                .build();
    }

    private FootballClub testCreateFootballClub_footballerNull() {
        return FootballClub.builder()
                .id(1L)
                .currency(Currency.TL.getValue())
                .clubManager("Fatih Terim")
                .clubName("Galatasaray SK")
                .build();
    }

    private Footballer testCreateFootballer() {
        return Footballer.builder()
                .id(2L)
                .age(30)
                .firstName("Radamel")
                .lastName("Falcao")
                .careerStartDate(LocalDate.of(2000, 7, 1))
                .contractBeginDate(LocalDate.of(2020, 7, 1))
                .contractEndDate(LocalDate.of(2023, 7, 1))
                .contractFee(100000d)
                .currency(Currency.TL.getValue())
                .transferFee(100000d)
                .footballClub(FootballClub.builder()
                        .id(3L)
                        .clubName("Galatasaray SK")
                        .build())
                .build();
    }
}
