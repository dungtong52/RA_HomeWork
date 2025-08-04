<%@ page import="com.ra.model.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/movie/updateMovieStyle.css">
    <title>Update Movie</title>
</head>
<body>
<div class="container">
    <h1>Sửa phim</h1>
    <%
        if (request.getAttribute("error") != null) {
    %>
    <p class="error"><%=request.getAttribute("error")%>
    </p>
    <%
        }
    %>
    <form action="<%=request.getContextPath()%>/movie?action=update" method="post">
        <%
            Movie movie = (Movie) request.getAttribute("movie");
            if (movie != null) {
        %>
        <label for="movieId">ID</label>
        <input type="number" id="movieId" name="movieId" readonly value="<%=movie.getMovieId()%>">

        <label for="title">Tiêu đề</label>
        <input type="text" id="title" name="title" required value="<%=movie.getTitle()%>">

        <label for="director">Đạo diễn</label>
        <input type="text" id="director" name="director" value="<%=movie.getDirector()%>">

        <label for="genre">Thể loại</label>
        <input type="text" id="genre" name="genre" value="<%=movie.getGenre()%>">

        <label for="description">Mô tả</label>
        <textarea name="description" id="description" rows="5"><%=movie.getDescription()%></textarea>

        <label for="duration">Thời gian (phút)</label>
        <input type="number" id="duration" name="duration" value="<%=movie.getDuration()%>">

        <label for="language">Ngôn ngữ</label>
        <input type="text" id="language" name="language" value="<%=movie.getLanguage()%>">

        <input type="submit" value="Sửa phim" class="btn-edit">
        <%
            }
        %>
    </form>
</div>
</body>
</html>
