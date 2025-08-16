package com.ra.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PostDto {
    @NotBlank(message = "Nội dung không được để trống")
    private String content;

    private MultipartFile image;
}
