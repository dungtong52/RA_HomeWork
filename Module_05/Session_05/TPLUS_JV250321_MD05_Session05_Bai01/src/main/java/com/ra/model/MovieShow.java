package com.ra.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MovieShow {
    private Movie movie;
    private List<Schedule> scheduleList;

    public MovieShow() {
        this.scheduleList = new ArrayList<>();
    }

    public MovieShow(Movie movie) {
        this.movie = movie;
        this.scheduleList = new ArrayList<>();
    }

    public void addSchedule(Schedule schedule) {
        this.scheduleList.add(schedule);
    }
}
