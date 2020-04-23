package com.akman.springbootdemo.service.footballclubservice;

import com.akman.springbootdemo.model.footbalclub.FootballClubRequest;
import com.akman.springbootdemo.model.footbalclub.FootballClubResponse;
import com.akman.springbootdemo.model.footballer.FootballerRequest;

import java.util.List;

public interface FootballClubService {

    List<FootballClubResponse> listFootballClubs();

    List<FootballClubResponse> listFootballClubsByClubName(String name);

    List<FootballClubResponse> listFootballClubsBySelectedFootballers(List<FootballerRequest> footballerRequestList);

    FootballClubResponse saveFootballClub(FootballClubRequest footballClubRequest);

    List<FootballClubResponse> removeFootballClubByClubName(String clubName);
}