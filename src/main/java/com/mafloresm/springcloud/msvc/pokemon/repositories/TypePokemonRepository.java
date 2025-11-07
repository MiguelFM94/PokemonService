package com.mafloresm.springcloud.msvc.pokemon.repositories;

import com.mafloresm.springcloud.msvc.pokemon.entities.TypePokemon;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TypePokemonRepository extends JpaRepository<TypePokemon, Long> {

}
