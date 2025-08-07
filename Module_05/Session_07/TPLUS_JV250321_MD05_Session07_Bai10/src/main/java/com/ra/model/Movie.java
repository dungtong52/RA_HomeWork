package com.ra.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Movie {
    private int id;

    @NotBlank(message = "Tiêu đề không được rỗng")
    @Size(max = 100, message = "Tiêu đề tối đa 100 ký tự")
    private String title;

    @NotBlank(message = "Tên đạo diễn không được để rỗng")
    @Size(max = 50, message = "Tên đạo diễn tối đa 50 ký tự")
    private String director;

    private LocalDate releaseDate;

    @NotBlank(message = "Thể loại không được rỗng")
    @Size(max = 30, message = "Thể loại tối đa 30 ký tự")
    private String genre;

    private String poster;
}
