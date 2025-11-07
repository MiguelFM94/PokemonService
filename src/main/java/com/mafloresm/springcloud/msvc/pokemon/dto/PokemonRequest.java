package com.mafloresm.springcloud.msvc.pokemon.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Data
public class PokemonRequest {

    private Long id;
    @NotBlank(message = "El nombre del pokemon no debe estar vacío")
    private String name;

    private Set<Long> typesId;

    public PokemonRequest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getTypesId() {
        return typesId;
    }

    public void setTypesId(Set<Long> typesId) {
        this.typesId = typesId;
    }
}
