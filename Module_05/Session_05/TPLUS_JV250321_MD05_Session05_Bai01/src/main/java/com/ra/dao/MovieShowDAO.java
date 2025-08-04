package com.ra.dao;

import com.ra.model.MovieShow;
import com.ra.model.ScreenRoom;

import java.util.List;

public interface MovieShowDAO {
    List<MovieShow> getMovieListShow();

    ScreenRoom getScreenRoomByScheduleId(long id);
}
