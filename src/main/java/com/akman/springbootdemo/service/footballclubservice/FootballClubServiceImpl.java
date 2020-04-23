package com.akman.springbootdemo.service.footballclubservice;

import com.akman.springbootdemo.model.enums.Currency;
import com.akman.springbootdemo.model.footbalclub.FootballClub;
import com.akman.springbootdemo.model.footbalclub.FootballClubRequest;
import com.akman.springbootdemo.model.footbalclub.FootballClubResponse;
import com.akman.springbootdemo.model.footballer.Footballer;
import com.akman.springbootdemo.model.footballer.FootballerRequest;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import com.akman.springbootdemo.repository.FootballClubRepository;
import com.akman.springbootdemo.request_response.RequestMapper;
import com.akman.springbootdemo.request_response.ResponseMapper;
import com.akman.springbootdemo.service.footballerservice.FootballerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FootballClubServiceImpl implements FootballClubService {

    private final FootballClubRepository footballClubRepository;
    private final FootballerService footballerService;

    @Override
    public List<FootballClubResponse> listFootballClubs() {
        return ResponseMapper.MAPPER.convertFootballClubListToFootballClubResponseList(footballClubRepository.findAll());
    }

    @Override
    public List<FootballClubResponse> listFootballClubsByClubName(String clubName) {
        return ResponseMapper.MAPPER.convertFootballClubListToFootballClubResponseList(footballClubRepository.findAllByClubName(clubName));
    }

    @Override
    public List<FootballClubResponse> listFootballClubsBySelectedFootballers(List<FootballerRequest> footballerRequestList) {
        List<Footballer> footballers = RequestMapper.MAPPER.convertFootballerRequestListToFootballerList(footballerRequestList);
        List<Long> footballersIds = footballers.stream()
                .map(Footballer::getId).collect(Collectors.toList());
        return ResponseMapper.MAPPER.convertFootballClubListToFootballClubResponseList(new ArrayList<>(footballClubRepository.findAllByFootballers_IdIn(footballersIds)));
    }

    @Override
    public FootballClubResponse saveFootballClub(FootballClubRequest footballClubRequest) {
        FootballClub footballClub = RequestMapper.MAPPER.convertFootballClubRequestToFootballClub(footballClubRequest);
        try {
            return ResponseMapper.MAPPER.convertFootballClubToFootballClubResponse(footballClubRepository.save(footballClub));
        } catch (Exception e) {
            log.error("Unique error while saving : " + e.getMessage(), e);
            return null;
        }
    }

    @Transactional
    @Override
    public List<FootballClubResponse> removeFootballClubByClubName(String clubName) {
        List<FootballerResponse> removedFootballers = footballerService.removeAllFootballerByClubName(clubName);
        List<FootballClubResponse> removedFootballClubs = ResponseMapper.MAPPER.convertFootballClubListToFootballClubResponseList(footballClubRepository.removeByClubName(clubName));
        removedFootballClubs.get(0).setFootballers(removedFootballers);
        return removedFootballClubs;
    }
}
