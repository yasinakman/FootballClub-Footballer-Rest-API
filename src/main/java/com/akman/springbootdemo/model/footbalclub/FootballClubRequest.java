package com.akman.springbootdemo.model.footbalclub;

import com.akman.springbootdemo.model.enums.Currency;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class FootballClubRequest {
    @ApiModelProperty(notes = "id")
    private Long id;
    @ApiModelProperty(notes = "club_name")
    private String clubName;
    @ApiModelProperty(notes = "club manager")
    private String clubManager;
    @ApiModelProperty(notes = "Currency")
    private Currency currency;
}