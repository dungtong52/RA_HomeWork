package com.ra.config;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dc6pp6hx7",
                "api_key", "846929762188599",
                "api_secret", "5AwLWFz8gy1TP8AigeINF6PgFf8",
                "secure", true
        ));
    }
}
