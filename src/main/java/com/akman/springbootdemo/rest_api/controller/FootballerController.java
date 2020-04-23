package com.akman.springbootdemo.rest_api.controller;

import com.akman.springbootdemo.model.footbalclub.FootballClubNameRequest;
import com.akman.springbootdemo.model.footballer.FootballerResponse;
import com.akman.springbootdemo.model.footballer.FootballerToSaveRequest;
import com.akman.springbootdemo.service.footballerservice.FootballerService;
import com.akman.springbootdemo.utils.ErrorConstants;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/footballer")
@Api(tags = {"Footballer"})
@SwaggerDefinition(tags = {
        @Tag(name = "Footballer", description = "List, save and edit footballer.")
})
public class FootballerController {

    private final FootballerService footballerService;

    @ApiOperation(value = "List footballer informations")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @RequestMapping(value = "/listFootballers", method = RequestMethod.GET)
    public ResponseEntity listFootballers() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(footballerService.listFootballers());
    }

    @ApiOperation(value = "List footballers information by club name")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @RequestMapping(value = "/listFootballersByClubName", method = RequestMethod.GET)
    public ResponseEntity listFootballersByClubName(@RequestParam(defaultValue = "Galatasaray SK") String clubName) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(footballerService.listFootballersByClubName(clubName));
    }

    @ApiOperation(value = "List footballers information by name")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @RequestMapping(value = "/listFootballersByName", method = RequestMethod.GET)
    public ResponseEntity listFootballersByName(@RequestParam(defaultValue = "yasin akman") String name) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(footballerService.listFootballersByName(name));
    }

    @ApiOperation(value = "List footballers with active contracts between given contract begin - end date and football club information")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @RequestMapping(value = "/listFootballerWithActiveContractsByFootballClubAndDate", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity listFootballersWithActiveContractsByFootballClubAndDate(@RequestBody @Valid List<FootballClubNameRequest> footballClubNameRequestList,
                                                                                  @RequestParam(defaultValue = "2020") String contractBeginDate,
                                                                                  @RequestParam(defaultValue = "2021") String contractEndDate) {
        List<FootballerResponse> footballerResponse = footballerService.listFootballerWithActiveContractsBySelectedFootballClubAndDate(footballClubNameRequestList, contractBeginDate, contractEndDate);
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(footballerResponse);

    }
    @ApiOperation(value = "save footballer in football club")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })

    @RequestMapping(value = "/saveFootballer", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveFootballer(@RequestBody @Valid FootballerToSaveRequest footballerToSaveRequest) {
        FootballerResponse savedFootballerInClub = footballerService.saveFootballer(footballerToSaveRequest);
        if (savedFootballerInClub != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedFootballerInClub);
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(ErrorConstants.FOOTBALLER_SAVE_ERROR);
        }
    }

    @ApiOperation(value = "List removed footballer informations")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @RequestMapping(value = "/removeAllFootballerByClubName", method = RequestMethod.GET)
    public ResponseEntity removeAllFootballerByClubName(@RequestParam(defaultValue = "Galatasaray SK") String clubName) {
        List<FootballerResponse> removedFootballers = footballerService.removeAllFootballerByClubName(clubName);
        if (removedFootballers != null && !StringUtils.isEmpty(removedFootballers)) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(removedFootballers);
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(ErrorConstants.FOOTBALLER_REMOVE_ERROR);
        }
    }

    @ApiOperation(value = "List removed footballer informations")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @RequestMapping(value = "/removeFootballerByName", method = RequestMethod.GET)
    public ResponseEntity removeFootballerByName(@RequestParam(defaultValue = "Radamel") String firstName,
                                                 @RequestParam(defaultValue = "Falcao") String lastName) {
        List<FootballerResponse> removedFootballers = footballerService.removeFootballerByName(firstName, lastName);
        if (removedFootballers != null && !StringUtils.isEmpty(removedFootballers)) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(removedFootballers);
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(ErrorConstants.FOOTBALLER_REMOVE_ERROR);
        }
    }
}
