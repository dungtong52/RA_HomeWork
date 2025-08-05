<%@ page import="java.util.List" %>
<%@ page import="com.ra.model.MovieShow" %>
<%@ page import="com.ra.model.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/homeStyle.css">
    <title>Home</title>
</head>
<body>
<h1>Danh sách phim đang chiếu</h1>
<div class="container">
    <%
        List<MovieShow> movieShowList = (List<MovieShow>) request.getAttribute("movieCard");
        if (movieShowList != null) {
            for (MovieShow movieShow : movieShowList) {
                Movie movie = movieShow.getMovie();
    %>
    <div class="movie-card">
        <h3><%=movie.getTitle()%>
        </h3>
        <p>Đạo diễn: <%=movie.getDirector()%>
        </p>
        <p>Thể loại: <%=movie.getGenre()%>
        </p>
        <a href="<%=request.getContextPath()%>/movie-detail?movieId=<%=movie.getMovieId()%>">Xem chi tiết</a>
    </div>
    <%
            }
        }
    %>
</div>
</body>
</html>