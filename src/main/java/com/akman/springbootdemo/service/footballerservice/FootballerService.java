package com.akman.springbootdemo.service.footballerservice;

import com.akman.springbootdemo.model.footbalclub.FootballClubNameRequest;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import com.akman.springbootdemo.model.footballer.FootballerToSaveRequest;

import java.util.List;

public interface FootballerService {

    List<FootballerResponse> listFootballers();

    List<FootballerResponse> listFootballersByClubName(String clubName);

    List<FootballerResponse> listFootballersByName(String name);

    List<FootballerResponse> listFootballerWithActiveContractsBySelectedFootballClubAndDate(List<FootballClubNameRequest> footballClubNameRequestList, String contractBeginDate, String contractEndDate);

    FootballerResponse saveFootballer(FootballerToSaveRequest footballerToSaveRequest);

    List<FootballerResponse> removeAllFootballerByClubName(String clubName);

    List<FootballerResponse> removeFootballerByName(String firstName, String lastName);
}