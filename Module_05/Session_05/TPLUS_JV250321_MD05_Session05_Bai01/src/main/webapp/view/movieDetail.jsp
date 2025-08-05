<%@ page import="com.ra.model.MovieShow" %>
<%@ page import="java.time.LocalDateTime" %>
<%@ page import="java.util.List" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page import="com.ra.model.Schedule" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/movieDetailStyle.css">
    <title>Movie Details</title>
</head>
<body>
<div class="container">
    <%
        MovieShow movieShow = (MovieShow) request.getAttribute("movieShow");
    %>
    <h1>Chi tiết phim: <%=movieShow.getMovie().getTitle()%>
    </h1>
    <p><b>Đạo diễn: </b><%=movieShow.getMovie().getDirector()%>
    </p>
    <p><b>Thể loại: </b><%=movieShow.getMovie().getGenre()%>
    </p>
    <p><b>Mô tả: </b><%=movieShow.getMovie().getDescription()%>
    </p>
    <p><b>Thời gian: </b><%=movieShow.getMovie().getDuration()%>
    </p>
    <p><b>Ngôn ngữ: </b><%=movieShow.getMovie().getLanguage()%>
    </p>
    <h3>Lịch chiếu:</h3>
    <%
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Schedule> scheduleList = movieShow.getScheduleList();
        for (Schedule schedule : scheduleList) {
    %>
    <div class="showtime-div">
        <a class="showtime"
           href="<%=request.getContextPath()%>/ticket?movieId=<%=movieShow.getMovie().getMovieId()%>&scheduleId=<%=schedule.getId()%>">
            <%=schedule.getShowTime().format(formatter)%>
        </a>
    </div>
    <%
        }
    %>
    <a href="<%=request.getContextPath()%>/home">Quay lại danh sách phim</a>
</div>
</body>
</html>
