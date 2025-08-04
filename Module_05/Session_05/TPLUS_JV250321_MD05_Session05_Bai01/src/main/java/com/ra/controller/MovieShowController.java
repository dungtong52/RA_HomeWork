package com.ra.controller;

import com.ra.model.MovieShow;
import com.ra.service.MovieShowService;
import com.ra.service.imp.MovieShowServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/home")
public class MovieShowController extends HttpServlet {
    private final MovieShowService movieShowService;

    public MovieShowController() {
        movieShowService = new MovieShowServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MovieShow> movieShowList = movieShowService.getMovieListShow();

        String movieIdStr = req.getParameter("movieId");
        if (movieIdStr != null) {
            try {
                long movieId = Long.parseLong(movieIdStr);

                MovieShow selectMovieShow = null;
                for (MovieShow movieShow : movieShowList) {
                    if (movieShow.getMovie().getMovieId() == movieId) {
                        selectMovieShow = movieShow;
                    }
                }

                if (selectMovieShow != null) {
                    req.setAttribute("movieDetail", selectMovieShow);
                    req.getRequestDispatcher("view/movieDetail.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        req.setAttribute("movieCard", movieShowList);
        req.getRequestDispatcher("view/home.jsp").forward(req, resp);
    }
}
