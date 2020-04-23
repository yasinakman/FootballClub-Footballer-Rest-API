package com.akman.springbootdemo.model.footbalclub;

import com.akman.springbootdemo.model.enums.Currency;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FootballClubResponse {
    @ApiModelProperty(notes = "id")
    private Long id;
    @ApiModelProperty(notes = "club_name")
    private String clubName;
    @ApiModelProperty(notes = "club manager")
    private String clubManager;
    @ApiModelProperty(notes = "Currency")
    private Currency currency;
    @ApiModelProperty(notes = "footballer")
    private List<FootballerResponse> footballers;
}