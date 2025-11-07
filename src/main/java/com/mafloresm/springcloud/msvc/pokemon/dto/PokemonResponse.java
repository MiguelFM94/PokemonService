package com.mafloresm.springcloud.msvc.pokemon.dto;

import com.mafloresm.springcloud.msvc.pokemon.models.PokemonTypeModel;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class PokemonResponse {

    private Long id;
    private String nombre;
    private PokemonTypeModel typePokemon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public PokemonTypeModel getTypePokemon() {
        return typePokemon;
    }

    public void setTypePokemon(PokemonTypeModel typePokemon) {
        this.typePokemon = typePokemon;
    }
}
