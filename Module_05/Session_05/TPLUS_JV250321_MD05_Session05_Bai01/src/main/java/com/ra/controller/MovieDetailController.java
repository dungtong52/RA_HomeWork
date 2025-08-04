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

@WebServlet("movie-detail")
public class MovieDetailController extends HttpServlet {
    private final MovieShowService movieShowService;

    public MovieDetailController() {
        movieShowService = new MovieShowServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieIdStr = req.getParameter("movieId");
        if (movieIdStr != null) {
            long movieId = Long.parseLong(movieIdStr);
            MovieShow selectedMovie = null;
            for (MovieShow movieShow : movieShowService.getMovieListShow()) {
                if (movieShow.getMovie().getMovieId() == movieId) {
                    selectedMovie = movieShow;
                    break;
                }
            }

            if (selectedMovie != null) {
                req.setAttribute("movieShow", selectedMovie);
                req.getRequestDispatcher("view/movieDetail.jsp").forward(req, resp);
                return;
            }
        }

        resp.sendRedirect(req.getContextPath() + "/home");
    }
}
