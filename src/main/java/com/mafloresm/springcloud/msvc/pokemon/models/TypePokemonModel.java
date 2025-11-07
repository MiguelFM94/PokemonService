package com.mafloresm.springcloud.msvc.pokemon.models;


import org.springframework.stereotype.Component;

@Component
public class TypePokemonModel {

    private Long typePokemonId;
    private String name;

    public Long getTypePokemonId() {
        return typePokemonId;
    }

    public void setTypePokemonId(Long typePokemonId) {
        this.typePokemonId = typePokemonId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

