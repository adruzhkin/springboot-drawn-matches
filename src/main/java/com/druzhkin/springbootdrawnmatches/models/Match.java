package com.druzhkin.springbootdrawnmatches.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Match {

    private String competition;
    private int year;
    private String round;
    private String teamOne;
    private String teamTwo;
    private String teamOneGoals;
    private String teamTwoGoals;

    public Match() {
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getRound() {
        return round;
    }

    public void setRound(String round) {
        this.round = round;
    }

    @JsonProperty("team1")
    public String getTeamOne() {
        return teamOne;
    }

    public void setTeamOne(String teamOne) {
        this.teamOne = teamOne;
    }

    @JsonProperty("team2")
    public String getTeamTwo() {
        return teamTwo;
    }

    public void setTeamTwo(String teamTwo) {
        this.teamTwo = teamTwo;
    }

    @JsonProperty("team1goals")
    public String getTeamOneGoals() {
        return teamOneGoals;
    }

    public void setTeamOneGoals(String teamOneGoals) {
        this.teamOneGoals = teamOneGoals;
    }

    @JsonProperty("team2goals")
    public String getTeamTwoGoals() {
        return teamTwoGoals;
    }

    public void setTeamTwoGoals(String teamTwoGoals) {
        this.teamTwoGoals = teamTwoGoals;
    }
}
