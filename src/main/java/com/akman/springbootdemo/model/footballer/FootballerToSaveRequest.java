package com.akman.springbootdemo.model.footballer;

import com.akman.springbootdemo.utils.LocalDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
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
public class FootballerToSaveRequest {
    @ApiModelProperty(notes = "id")
    private Long id;
    @ApiModelProperty(notes = "first_name")
    private String firstName;
    @ApiModelProperty(notes = "last_name")
    private String lastName;
    @ApiModelProperty(notes = "Age")
    private Integer age;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(notes = "Career start date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate careerStartDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(notes = "contract_begin_year")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractBeginDate;
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @ApiModelProperty(notes = "contract_end_year")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate contractEndDate;
    @ApiModelProperty(notes = "football_club")
    private String footballClub;
}