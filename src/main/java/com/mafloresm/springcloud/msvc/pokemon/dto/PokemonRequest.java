package com.mafloresm.springcloud.msvc.pokemon.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

@Component
public class PokemonRequest {

    private Long id;
    @NotBlank(message = "El nombre no debe estar vacio")
    private String nombre;

    @Valid
    @NotNull(message = "Debe indicar el tipo de Pokémon")
    private TypePokemonRequest typePokemon;

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

    public TypePokemonRequest getTypePokemon() {
        return typePokemon;
    }

    public void setTypePokemon(TypePokemonRequest typePokemon) {
        this.typePokemon = typePokemon;
    }
}
