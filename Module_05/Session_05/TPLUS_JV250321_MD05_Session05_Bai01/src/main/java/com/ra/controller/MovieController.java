package com.ra.controller;

import com.ra.model.Movie;
import com.ra.service.MovieService;
import com.ra.service.imp.MovieServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/movie")
public class MovieController extends HttpServlet {
    private final MovieService movieService;

    public MovieController() {
        movieService = new MovieServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        long movieId;
        Movie movie;
        switch (action) {
            case "list":
                findAllMovie(req, resp);
                break;
            case "edit":
                movieId = Long.parseLong(req.getParameter("movieId"));
                movie = movieService.getMovieById(movieId);
                req.setAttribute("movie", movie);
                req.getRequestDispatcher("view/movie/updateMovie.jsp").forward(req, resp);
                break;
            case "delete":
                movieId = Long.parseLong(req.getParameter("movieId"));
                boolean success = movieService.deleteMovie(movieId);
                if (success) {
                    findAllMovie(req, resp);
                } else {
                    String error = "Có lỗi trong quá trình xóa phim";
                    req.setAttribute("error", error);
                    req.getRequestDispatcher("view/movie/listMovies.jsp").forward(req, resp);
                }
        }
    }

    public void findAllMovie(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Movie> movieList = movieService.getAllMovies();
        request.setAttribute("list", movieList);
        request.getRequestDispatcher("view/movie/listMovies.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action.equals("save")) {
            Movie movie = setMovie(req);
            boolean success = movieService.addMovie(movie);
            if (success) {
                findAllMovie(req, resp);
            } else {
                String error = "Có lỗi trong quá trình thêm phim mới";
                req.setAttribute("error", error);
                req.getRequestDispatcher("view/movie/addMovie.jsp").forward(req, resp);
            }
        } else if (action.equals("update")) {
            Movie movie = setMovie(req);
            boolean success = movieService.updateMovie(movie);
            if (success) {
                findAllMovie(req, resp);
            } else {
                String error = "Có lỗi trong quá trình sửa phim";
                req.setAttribute("error", error);
                req.getRequestDispatcher("view/movie/updateMovie.jsp").forward(req, resp);
            }
        }
    }

    public Movie setMovie(HttpServletRequest request){
        Movie movie = new Movie();
        String movieIdStr = request.getParameter("movieId");
        if(movieIdStr != null && !movieIdStr.isEmpty()){
            movie.setMovieId(Long.parseLong(movieIdStr));
        }
        movie.setTitle(request.getParameter("title"));
        movie.setDirector(request.getParameter("director"));
        movie.setGenre(request.getParameter("genre"));
        movie.setDescription(request.getParameter("description"));
        movie.setDuration(Integer.parseInt(request.getParameter("duration")));
        movie.setLanguage(request.getParameter("language"));
        return movie;
    }
}
