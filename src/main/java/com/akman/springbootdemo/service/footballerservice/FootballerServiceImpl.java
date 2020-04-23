package com.akman.springbootdemo.service.footballerservice;

import com.akman.springbootdemo.model.enums.Currency;
import com.akman.springbootdemo.model.footbalclub.FootballClub;
import com.akman.springbootdemo.model.footbalclub.FootballClubNameRequest;
import com.akman.springbootdemo.model.footballer.Footballer;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import com.akman.springbootdemo.model.footballer.FootballerToSaveRequest;
import com.akman.springbootdemo.repository.FootballClubRepository;
import com.akman.springbootdemo.repository.FootballerRepository;
import com.akman.springbootdemo.request_response.RequestMapper;
import com.akman.springbootdemo.request_response.ResponseMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FootballerServiceImpl implements FootballerService {

    private final FootballerRepository footballerRepository;
    private final FootballClubRepository footballClubRepository;

    @Override
    public List<FootballerResponse> listFootballers() {
        return ResponseMapper.MAPPER.convertFootballerListToFootballerResponseList(footballerRepository.findAll());
    }

    @Override
    public List<FootballerResponse> listFootballersByClubName(String clubName) {
        return ResponseMapper.MAPPER.convertFootballerListToFootballerResponseList(footballerRepository.findByFootballClub_ClubName(clubName));
    }

    @Override
    public List<FootballerResponse> listFootballersByName(String name) {
        return ResponseMapper.MAPPER.convertFootballerListToFootballerResponseList(footballerRepository.findByFirstNameAndLastName(name));
    }

    @Override
    public List<FootballerResponse> listFootballerWithActiveContractsBySelectedFootballClubAndDate(List<FootballClubNameRequest> footballClubNameRequestList, String contractBeginDate, String contractEndDate) {
        LocalDate beginYearEndOfYear = LocalDate.of(Integer.parseInt(contractBeginDate), 1, 1).with(TemporalAdjusters.lastDayOfYear());
        LocalDate endYearBeginOfYear = LocalDate.of(Integer.parseInt(contractEndDate), 1, 1);
        List<Long> footballClubIdList = footballClubNameRequestList.stream()
                .map(FootballClubNameRequest::getId).collect(Collectors.toList());
        List<Footballer> footballerList = footballerRepository.findAllByFootballClub_IdInAndContractBeginDateLessThanEqualAndContractEndDateGreaterThanEqual
                (footballClubIdList, beginYearEndOfYear, endYearBeginOfYear);
        return ResponseMapper.MAPPER.convertFootballerListToFootballerResponseList(footballerList);
    }

    @Override
    public FootballerResponse saveFootballer(FootballerToSaveRequest footballerToSaveRequest) {
        FootballClub footballClub = footballClubRepository.findByClubName(footballerToSaveRequest.getFootballClub());
        if (footballClub != null) {
            Footballer footballer = RequestMapper.MAPPER.convertFootballerToSaveRequestToFootballer(footballerToSaveRequest);
            long elapsedMonth = ChronoUnit.MONTHS.between(footballerToSaveRequest.getCareerStartDate(), LocalDate.now());
            Double transferFee = (elapsedMonth * 100000d) / footballerToSaveRequest.getAge();
            Double contractFee = transferFee * 1.1d;
            Currency currency = footballClub.getCurrency();
            footballer.setTransferFee(transferFee);
            footballer.setContractFee(contractFee);
            footballer.setCurrency(currency);
            footballer.setFootballClub(footballClub);
            footballer.getFootballClub().setFootballers(null);
            try {
                return ResponseMapper.MAPPER.convertFootballerToFootballerResponse(footballerRepository.save(footballer));
            } catch (Exception e) {
                log.error("Save error : " + e.getMessage(), e);
                return null;
            }
        }
        log.error("Football club isn't existing : ");
        return null;
    }

    @Transactional
    @Override
    public List<FootballerResponse> removeAllFootballerByClubName(String clubName) {
        return ResponseMapper.MAPPER.convertFootballerListToFootballerResponseList(footballerRepository.removeAllByFootballClub_ClubName(clubName));
    }

    @Transactional
    @Override
    public List<FootballerResponse> removeFootballerByName(String firstName, String lastName) {
        return ResponseMapper.MAPPER.convertFootballerListToFootballerResponseList(footballerRepository.removeByFirstNameAndLastName(firstName, lastName));
    }
}
