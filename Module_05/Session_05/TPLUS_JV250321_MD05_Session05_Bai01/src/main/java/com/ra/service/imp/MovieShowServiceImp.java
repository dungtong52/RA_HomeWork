package com.ra.service.imp;

import com.ra.dao.MovieShowDAO;
import com.ra.dao.imp.MovieShowDAOImp;
import com.ra.model.MovieShow;
import com.ra.model.ScreenRoom;
import com.ra.service.MovieShowService;

import java.util.List;

public class MovieShowServiceImp implements MovieShowService {
    private final MovieShowDAO movieShowDAO;

    public MovieShowServiceImp() {
        movieShowDAO = new MovieShowDAOImp();
    }

    @Override
    public List<MovieShow> getMovieListShow() {
        return movieShowDAO.getMovieListShow();
    }

    @Override
    public ScreenRoom getScreenRoomByScheduleId(long id) {
        return movieShowDAO.getScreenRoomByScheduleId(id);
    }
}
