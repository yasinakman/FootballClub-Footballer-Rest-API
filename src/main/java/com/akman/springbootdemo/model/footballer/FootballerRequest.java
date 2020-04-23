package com.akman.springbootdemo.model.footballer;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FootballerRequest {
    @ApiModelProperty(notes = "id")
    private Long id;
    @ApiModelProperty(notes = "first_name")
    private String firstName;
    @ApiModelProperty(notes = "last_name")
    private String lastName;
}