package com.akman.springbootdemo.rest_api.controller;

import com.akman.springbootdemo.model.footbalclub.FootballClubRequest;
import com.akman.springbootdemo.model.footbalclub.FootballClubResponse;
import com.akman.springbootdemo.model.footballer.FootballerRequest;
import com.akman.springbootdemo.service.footballclubservice.FootballClubService;
import com.akman.springbootdemo.utils.ErrorConstants;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/footballClub")
@Api(tags = {"FootballClub"})
@SwaggerDefinition(tags = {
        @Tag(name = "FootballClub", description = "List, save and edit footbal clubs.")
})
public class FootballClubController {

    private final FootballClubService footballClubService;

    @ApiOperation(value = "List football clubs")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @RequestMapping(value = "/listFootballClubs", method = RequestMethod.GET)
    public ResponseEntity listFootballClubs() {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(footballClubService.listFootballClubs());
    }

    @ApiOperation(value = "List football clubs information by club name")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
    })
    @RequestMapping(value = "/listFootballClubsByClubName", method = RequestMethod.GET)
    public ResponseEntity listFootballClubsByClubName(@RequestParam(defaultValue = "Galatasaray SK") String clubName) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(footballClubService.listFootballClubsByClubName(clubName));
    }

    @ApiOperation(value = "List football clubs by footballers")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
    })
    @RequestMapping(value = "/listFootballClubsByFootballers", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
    public ResponseEntity listFootballClubsByFootballers(@RequestBody @Valid List<FootballerRequest> footballerRequestList) {
        return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body(footballClubService.listFootballClubsBySelectedFootballers(footballerRequestList));
    }

    @ApiOperation(value = "save football club")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })

    @RequestMapping(value = "/saveFootballClub", method = RequestMethod.POST, produces = "application/json")
    public ResponseEntity saveFootballClub(@RequestBody FootballClubRequest footballClubRequest) {

        FootballClubResponse savedFootballClub = footballClubService.saveFootballClub(footballClubRequest);
        if (savedFootballClub != null) {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(savedFootballClub);
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(ErrorConstants.FOOTBALL_CLUB_SAVE_ERROR);
        }
    }

    @ApiOperation(value = "List user informations")
    @ApiResponses(value = {
            @ApiResponse(code = 202, message = "Accepted"),
            @ApiResponse(code = 403, message = "Forbidden"),
            @ApiResponse(code = 404, message = "Not Found"),
            @ApiResponse(code = 422, message = "Unprocessable entity")
    })
    @RequestMapping(value = "/removeFootballClubByName", method = RequestMethod.GET)
    public ResponseEntity removeFootballClubByName(@RequestParam(defaultValue = "Galatasaray SK") String clubName) {
        List<FootballClubResponse> removedFootballClub = footballClubService.removeFootballClubByClubName(clubName);
        if (removedFootballClub != null && !StringUtils.isEmpty(removedFootballClub)) {
            return ResponseEntity
                    .status(HttpStatus.ACCEPTED)
                    .body(removedFootballClub);
        } else {
            return ResponseEntity
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .body(ErrorConstants.FOOTBALL_CLUB_REMOVE_ERROR);
        }
    }
}
