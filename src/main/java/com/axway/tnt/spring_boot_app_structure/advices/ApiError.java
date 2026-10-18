package com.axway.tnt.spring_boot_app_structure.advices;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ApiError {
    private HttpStatus status;
    private String message;
}
