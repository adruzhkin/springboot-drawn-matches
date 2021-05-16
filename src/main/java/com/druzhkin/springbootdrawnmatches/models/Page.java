package com.druzhkin.springbootdrawnmatches.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Page {

    private int page;
    private int perPage;
    private int total;
    private int totalPages;
    private List<Match> data;

    public Page() {
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    @JsonProperty("per_page")
    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    @JsonProperty("total_pages")
    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<Match> getData() {
        return data;
    }

    public void setData(List<Match> data) {
        this.data = data;
    }
}
