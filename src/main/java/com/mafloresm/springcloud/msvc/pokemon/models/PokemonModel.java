package com.mafloresm.springcloud.msvc.pokemon.models;

import org.springframework.stereotype.Component;
import java.util.Set;

@Component
public class PokemonModel {

    private Long idPokemon;
    private String name;
    private Set<TypePokemonModel> typePokemon;

    public Long getIdPokemon() {
        return idPokemon;
    }

    public void setIdPokemon(Long idPokemon) {
        this.idPokemon = idPokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<TypePokemonModel> getTypePokemon() {
        return typePokemon;
    }

    public void setTypePokemon(Set<TypePokemonModel> typePokemon) {
        this.typePokemon = typePokemon;
    }
}
