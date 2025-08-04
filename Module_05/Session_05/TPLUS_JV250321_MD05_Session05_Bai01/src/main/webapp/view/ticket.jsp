<%@ page import="com.ra.model.Movie" %>
<%@ page import="com.ra.model.Schedule" %>
<%@ page import="com.ra.model.ScreenRoom" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ra.model.AccountSession" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/ticketStyle.css">
    <title>Buy Ticket</title>
</head>
<body>
<%
    Movie movie = (Movie) request.getAttribute("movie");
    Schedule schedule = (Schedule) request.getAttribute("schedule");
    ScreenRoom screenRoom = (ScreenRoom) request.getAttribute("screenRoom");
%>
<h1>Đặt vé xem phim</h1>
<div class="movie-detail">
    <p>Tên phòng chiếu: <%=screenRoom.getId()%>
    </p>
    <p>Tên phim: <%=movie.getTitle()%>
    </p>
    <p>Thời gian chiếu: <%=schedule.getShowTime()%>
    </p>
</div>
<div>
    <% if (request.getAttribute("message") != null) { %>
    <p class="message"><%= request.getAttribute("message") %>
    </p>
    <% } %>
    <% if (request.getAttribute("error") != null) { %>
    <p class="error"><%= request.getAttribute("error") %>
    </p>
    <% } %>
</div>
<form action="<%=request.getContextPath()%>/buy-ticket" method="post">
    <input type="hidden" name="scheduleId" value="<%=request.getParameter("scheduleId")%>">
    <input type="hidden" name="accountId" value="<%=AccountSession.currentAcount.getAccountId()%>">
    <input type="hidden" id="seatNumberInput" name="seatNumber">

    <div class="seat">
        <%
            List<String> bookSeat = (List<String>) request.getAttribute("bookSeat");
            String[] rows = {"A", "B", "C", "D", "E", "F", "G", "H"};
            int cols = 10;
            for (String row : rows) {
                for (int i = 1; i <= cols; i++) {
                    String seatCode = row + i;
                    boolean isBooked = bookSeat != null && bookSeat.contains(seatCode);
        %>
        <button type="button"
                class="btn-seat <%= isBooked ? "booked" : ""%>"
                <%= isBooked ? "disabled" : "" %>
                onclick="selectSeat('<%=seatCode%>', this)">
            <%=seatCode%>
        </button>
        <%
                }
            }
        %>
    </div>
    <button type="submit">Đặt vé</button>
</form>

<script>
    function selectSeat(seatCode, element) {
        document.getElementById("seatNumberInput").value = seatCode;
        document.querySelectorAll(".btn-seat").forEach(btn => btn.classList.remove("selected"));
        element.classList.add("selected");
    }
</script>
</body>
</html>

