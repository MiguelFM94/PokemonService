package com.mafloresm.springcloud.msvc.pokemon.models;


import org.springframework.stereotype.Component;

@Component
public class PokemonTypeModel{

    private Long id;
    private String nombre;

    public PokemonTypeModel() {

    }

    public PokemonTypeModel(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

