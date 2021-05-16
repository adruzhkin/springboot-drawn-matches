package com.druzhkin.springbootdrawnmatches.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class DrawnMatchesInfo {

    private int year;
    private int drawnMatches;

    public DrawnMatchesInfo() {
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @JsonProperty("drawn_matches")
    public int getDrawnMatches() {
        return drawnMatches;
    }

    public void setDrawnMatches(int drawnMatches) {
        this.drawnMatches = drawnMatches;
    }
}
