package com.akman.springbootdemo.request_response;

import com.akman.springbootdemo.model.footbalclub.FootballClub;
import com.akman.springbootdemo.model.footbalclub.FootballClubRequest;
import com.akman.springbootdemo.model.footballer.Footballer;
import com.akman.springbootdemo.model.footballer.FootballerRequest;
import com.akman.springbootdemo.model.footballer.FootballerToSaveRequest;
import fr.xebia.extras.selma.*;

import java.util.List;

@Mapper
public interface RequestMapper {

    RequestMapper MAPPER = Selma.builder(RequestMapper.class).build();

    @Maps(withIgnoreMissing = IgnoreMissing.ALL)
    FootballClub convertFootballClubRequestToFootballClub(FootballClubRequest footballClubRequest);

    @Maps(withIgnoreMissing = IgnoreMissing.ALL,
            withCustomFields = {
                    @Field({"footballClub", "footballClub.clubName"})
            })
    Footballer convertFootballerRequestToFootballer(FootballerRequest footballerRequest);
    @Maps(withIgnoreMissing = IgnoreMissing.ALL)
    List<Footballer> convertFootballerRequestListToFootballerList(List<FootballerRequest> footballerRequestList);

    @Maps(withIgnoreMissing = IgnoreMissing.ALL,
            withCustomFields = {
                    @Field({"footballClub", "footballClub.clubName"})
            })
    Footballer convertFootballerToSaveRequestToFootballer(FootballerToSaveRequest footballerToSaveRequest);
}
