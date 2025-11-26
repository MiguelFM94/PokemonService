package com.mafloresm.springcloud.msvc.pokemon.dto;

import com.mafloresm.springcloud.msvc.pokemon.models.PokemonModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.List;


@Component
public class PokemonResponse {

    private List<PokemonModel> data;
    private HttpStatus status;
    private String message;

    public PokemonResponse() {
    }

    public PokemonResponse(List<PokemonModel> data, HttpStatus status, String message) {
        this.data = data;
        this.status = status;
        this.message = message;
    }

    public List<PokemonModel> getData() {
        return data;
    }

    public void setData(List<PokemonModel> data) {
        this.data = data;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
