package com.mafloresm.springcloud.msvc.pokemon.exceptions;

import org.springframework.stereotype.Component;

@Component
public class PokemonException extends RuntimeException {
    private int httpStatus;

    public PokemonException() {

    }

    public PokemonException(String message, int httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
    }

    public int getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(int httpStatus) {
        this.httpStatus = httpStatus;
    }
}
