package com.akman.springbootdemo.request_response;

import com.akman.springbootdemo.model.footbalclub.FootballClub;
import com.akman.springbootdemo.model.footbalclub.FootballClubResponse;
import com.akman.springbootdemo.model.footballer.Footballer;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import fr.xebia.extras.selma.Field;
import fr.xebia.extras.selma.IgnoreMissing;
import fr.xebia.extras.selma.Mapper;
import fr.xebia.extras.selma.Maps;
import fr.xebia.extras.selma.Selma;

import java.util.List;

@Mapper
public interface ResponseMapper {

    ResponseMapper MAPPER = Selma.builder(ResponseMapper.class).build();

    @Maps(withIgnoreMissing = IgnoreMissing.ALL)
    FootballClubResponse convertFootballClubToFootballClubResponse(FootballClub footballClub);
    @Maps(withIgnoreMissing = IgnoreMissing.ALL)
    List<FootballClubResponse> convertFootballClubListToFootballClubResponseList(List<FootballClub> footballClubList);

    @Maps(withIgnoreMissing = IgnoreMissing.ALL,
            withCustomFields = {
                    @Field({"footballClub.clubName", "footballClub"})
            })
    FootballerResponse convertFootballerToFootballerResponse(Footballer footballer);
    @Maps(withIgnoreMissing = IgnoreMissing.ALL)
    List<FootballerResponse> convertFootballerListToFootballerResponseList(List<Footballer> footballerList);
}