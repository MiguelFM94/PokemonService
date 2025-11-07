package com.mafloresm.springcloud.msvc.pokemon.models;

import org.springframework.stereotype.Component;

@Component
public class PokemonModel {

    private Long id;
    private String nombre;
    private PokemonTypeModel pokemonTypeModel;


    public PokemonModel() {
    }

    public PokemonModel(Long id, String nombre, PokemonTypeModel pokemonTypeModel) {
        this.id = id;
        this.nombre = nombre;
        this.pokemonTypeModel = pokemonTypeModel;
    }

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
        return pokemonTypeModel;
    }

    public void setTypePokemon(PokemonTypeModel pokemonTypeModel) {
        this.pokemonTypeModel = pokemonTypeModel;
    }
}
