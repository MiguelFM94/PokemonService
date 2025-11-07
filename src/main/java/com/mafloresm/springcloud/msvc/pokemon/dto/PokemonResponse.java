package com.mafloresm.springcloud.msvc.pokemon.dto;

import com.mafloresm.springcloud.msvc.pokemon.models.PokemonTypeModel;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component

public class PokemonResponse {

    private Long id;
    private String name;
    private Set<String> types; // solo los nombres de los tipos

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

    public Set<String> getTypes() {
        return types;
    }

    public void setTypes(Set<String> types) {
        this.types = types;
    }
}
