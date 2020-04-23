package com.akman.springbootdemo.model.footbalclub;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ApiModel
public class FootballClubNameRequest {
    @NotNull
    @ApiModelProperty(notes = "id")
    private Long id;
    @ApiModelProperty(notes = "club_name")
    private String clubName;
}