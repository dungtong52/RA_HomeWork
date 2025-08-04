package com.ra.service;

import com.ra.model.MovieShow;
import com.ra.model.ScreenRoom;

import java.util.List;

public interface MovieShowService {
    List<MovieShow> getMovieListShow();

    ScreenRoom getScreenRoomByScheduleId(long id);
}
