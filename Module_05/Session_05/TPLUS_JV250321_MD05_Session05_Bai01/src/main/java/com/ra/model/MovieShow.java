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
    private List<LocalDateTime> showtimeList;

    public MovieShow(Movie movie) {
        this.movie = movie;
        this.showtimeList = new ArrayList<>();
    }

    public void addShowtime(LocalDateTime showtime){
        this.showtimeList.add(showtime);
    }
}
