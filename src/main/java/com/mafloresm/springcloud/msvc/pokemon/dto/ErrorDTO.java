package com.mafloresm.springcloud.msvc.pokemon.dto;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;


public class ErrorDTO{
    private String error;
    private Map<String, String> details;
    private int httpStatus;
    private LocalDateTime date;


    public ErrorDTO(String error, Map<String, String> details, int httpStatus, LocalDateTime date) {
        this.error = error;
        this.details = details;
        this.httpStatus = httpStatus;
        this.date = date;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Map<String, String> getDetails() {
        return details;
    }

    public void setDetails(Map<String, String> details) {
        this.details = details;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
