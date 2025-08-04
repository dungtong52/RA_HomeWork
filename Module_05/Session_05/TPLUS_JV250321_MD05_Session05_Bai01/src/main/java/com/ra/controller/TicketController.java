package com.ra.controller;

import com.ra.model.MovieShow;
import com.ra.model.Schedule;
import com.ra.model.ScreenRoom;
import com.ra.service.MovieShowService;
import com.ra.service.TicketService;
import com.ra.service.imp.MovieShowServiceImp;
import com.ra.service.imp.TicketServiceImp;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/ticket")
public class TicketController extends HttpServlet {
    private final MovieShowService movieShowService;
    private final TicketService ticketService;

    public TicketController() {
        movieShowService = new MovieShowServiceImp();
        ticketService = new TicketServiceImp();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String movieIdStr = req.getParameter("movieId");
        String scheduleIdStr = req.getParameter("scheduleId");

        if (movieIdStr != null && scheduleIdStr != null) {
            long movieId = Long.parseLong(movieIdStr);
            long scheduleId = Long.parseLong(scheduleIdStr);

            for (MovieShow movieShow : movieShowService.getMovieListShow()) {
                if (movieShow.getMovie().getMovieId() == movieId) {
                    for (Schedule schedule : movieShow.getScheduleList()) {
                        if (schedule.getId() == scheduleId) {
                            ScreenRoom screenRoom = movieShowService.getScreenRoomByScheduleId(scheduleId);
                            req.setAttribute("movie", movieShow.getMovie());
                            req.setAttribute("schedule", schedule);
                            req.setAttribute("screenRoom", screenRoom);
                            req.getRequestDispatcher("view/ticket.jsp").forward(req, resp);
                            return;
                        }
                    }
                }
            }
        }

        resp.sendRedirect(req.getContextPath() + "/home");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long scheduleId = Long.parseLong(req.getParameter("scheduleId"));
        long accountId = Long.parseLong(req.getParameter("accountId"));
        String seatNumber = req.getParameter("seatNumber");

        boolean success = ticketService.bookTicket(scheduleId, accountId, seatNumber);
        List<String> bookedSeats = ticketService.getSeatByScheduleId(scheduleId);
        req.setAttribute("bookSeat", bookedSeats);
        req.setAttribute("scheduleId", scheduleId);

        if (success) {
            req.setAttribute("message", "Đặt vé thành công!");
        } else {
            req.setAttribute("error", "Đặt vé thất bại! Ghế đã có người đặt.");
        }

        req.getRequestDispatcher("view/ticket.jsp").forward(req, resp);
    }
}
