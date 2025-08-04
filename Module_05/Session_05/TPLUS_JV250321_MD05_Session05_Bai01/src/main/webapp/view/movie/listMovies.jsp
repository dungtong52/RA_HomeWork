<%@ page import="java.util.List" %>
<%@ page import="com.ra.model.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/movie/listMoviesStyle.css">
    <title>List Movies</title>
</head>
<body>
<div class="container">
    <h1>Danh sách phim</h1>
    <a href="<%=request.getContextPath()%>/view/movie/addMovie.jsp" class="btn-add">Thêm phim mới</a>
    <table>
        <thead>
        <tr>
            <th class="col-id">Mã phim</th>
            <th class="col-title">Tiêu đề</th>
            <th class="col-director">Đạo diễn</th>
            <th class="col-genre">Thể loại</th>
            <th class="col-description">Mô tả</th>
            <th class="col-duration">Thời lượng</th>
            <th class="col-language">Ngôn ngữ</th>
            <th class="col-action">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Movie> movieList = (List<Movie>) request.getAttribute("list");
            if (movieList != null) {
                for (Movie movie : movieList) {
        %>
        <tr>
            <td><%=movie.getMovieId()%>
            </td>
            <td><%=movie.getTitle()%>
            </td>
            <td><%=movie.getDirector()%>
            </td>
            <td><%=movie.getGenre()%>
            </td>
            <td><%=movie.getDescription()%>
            </td>
            <td><%=movie.getDuration()%>
            </td>
            <td><%=movie.getLanguage()%>
            </td>
            <td>
                <a href="<%=request.getContextPath()%>/movie?action=edit&movieId=<%=movie.getMovieId()%>"
                   class="btn-edit">Sửa</a>
                <a href="<%=request.getContextPath()%>/movie?action=delete&movieId=<%=movie.getMovieId()%>"
                   class="btn-delete"
                   onclick="return confirm('Bạn có chắc chắn muốn xóa phim này không?');">Xóa</a>
            </td>
        </tr>
        <%
                }
            }
        %>
        </tbody>
    </table>
    <%
        if (request.getAttribute("error") != null) {
    %>
    <p class="error"><%=request.getAttribute("error")%>
    </p>
    <%
        }
    %>
</div>
</body>
</html>
