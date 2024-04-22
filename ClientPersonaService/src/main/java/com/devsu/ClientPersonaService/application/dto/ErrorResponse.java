package com.devsu.ClientPersonaService.application.dto;

import lombok.Builder;

@Builder
public class ErrorResponse {
    private String message;
    private String details;
}