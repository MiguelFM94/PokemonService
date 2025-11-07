package com.mafloresm.springcloud.msvc.pokemon.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.hibernate.annotations.Where;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table
@Where(clause = "active = true")
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pokemon_id")
    private Long idPokemon;

    @NotBlank(message = "El nombre del pokemon no debe estar vacío")
    private String name;

//    @ManyToOne
//    @JoinColumn(name = "type_pokemon_id") // la columna FK en la tabla 'pokemon'
//    private TypePokemon type;

    @ManyToMany
    @JoinTable(
            name="pokemon_types",
            joinColumns= @JoinColumn(name="pokemon_id"),
            inverseJoinColumns =@JoinColumn(name="type_id")
    )
    private Set<TypePokemon> types = new HashSet<>();

    private boolean active = true;

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

    public Set<TypePokemon> getTypes() {
        return types;
    }

    public void setTypes(Set<TypePokemon> types) {
        this.types = types;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
