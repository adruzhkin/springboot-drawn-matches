package com.druzhkin.springbootdrawnmatches.services;

import com.druzhkin.springbootdrawnmatches.models.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class MatchesServiceImpl implements MatchesService {

    private final RestTemplate restTemplate;

    @Autowired
    public MatchesServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @Override
    public int countDrawnMatches(int year) {
        int drawnMatches = 0;

        String baseURL = "https://jsonmock.hackerrank.com/api/football_matches?year=" + year;

        for (int score = 0; ;score++) {
            String pageURL = baseURL + "&team1goals=" + score + "&team2goals=" + score;
            Page page = restTemplate.getForObject(pageURL, Page.class);

            if (page.getTotal() == 0) break;
            drawnMatches += page.getTotal();
        }

        return drawnMatches;
    }

}
