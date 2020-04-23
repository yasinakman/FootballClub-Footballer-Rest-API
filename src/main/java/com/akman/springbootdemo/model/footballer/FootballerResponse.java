package com.akman.springbootdemo.model.footballer;

import com.akman.springbootdemo.utils.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FootballerResponse {
    @ApiModelProperty(notes = "id")
    private Long id;
    @ApiModelProperty(notes = "first_name")
    private String firstName;
    @ApiModelProperty(notes = "last_name")
    private String lastName;
    @ApiModelProperty(notes = "Age")
    private Integer age;
    @ApiModelProperty(notes = "Transfer fee")
    private Double transferFee;
    @ApiModelProperty(notes = "Contract fee")
    private Double contractFee;
    @ApiModelProperty(notes = "Currency")
    private String currency;
    @ApiModelProperty(notes = "Career start date")
    private LocalDate careerStartDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiModelProperty(notes = "contract_begin_year")
    private LocalDate contractBeginDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @ApiModelProperty(notes = "contract_end_year")
    private LocalDate contractEndDate;
    @ApiModelProperty(notes = "football_club")
    private String footballClub;
}