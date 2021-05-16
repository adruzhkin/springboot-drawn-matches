package com.druzhkin.springbootdrawnmatches.controllers;

import com.druzhkin.springbootdrawnmatches.messages.DrawnMatchesInfo;
import com.druzhkin.springbootdrawnmatches.services.MatchesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/public/matches")
public class MatchesPublicController {

    private final MatchesService matchesService;

    @Autowired
    public MatchesPublicController(MatchesService matchesService) {
        this.matchesService = matchesService;
    }

    @GetMapping("/drawn/{year}")
    public ResponseEntity<DrawnMatchesInfo> countDrawnMatches(@PathVariable("year") int year) {

        int drawnMatches = matchesService.countDrawnMatches(year);

        DrawnMatchesInfo response = new DrawnMatchesInfo();
        response.setYear(year);
        response.setDrawnMatches(drawnMatches);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
