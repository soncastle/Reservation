package com.reservation.movie.config;

import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "Reservation API",
        description = "영화 기반 주점 좌석 예약 API 문서",
        version = "v1"
    )
)
public class SwaggerConfig {}