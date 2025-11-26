package com.mafloresm.springcloud.msvc.pokemon.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.HashSet;
import java.util.Set;

@Entity
public class TypePokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "type_id")
    private Long id;

    @NotBlank(message = "El nombre del tipo no debe estar vacío")
    private String name;

    @ManyToMany(mappedBy = "types")
    private Set<Pokemon> pokemon = new HashSet<>();

    public Long getId() { 
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Pokemon> getPokemon() {
        return pokemon;
    }

    public void setPokemon(Set<Pokemon> pokemon) {
        this.pokemon = pokemon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
