package com.mafloresm.springcloud.msvc.pokemon.services;

import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonRequest;
import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonResponse;
import org.springframework.stereotype.Repository;


@Repository
public interface PokemonService {

    PokemonResponse findAll();
    PokemonResponse getById(Long id);
    PokemonResponse getByName(String nombre);
    PokemonResponse savePokemon(PokemonRequest pokemonRequest);
    PokemonResponse updatePokemon(PokemonRequest pokemonRequest);
    PokemonResponse deletePokemon(Long id);
}
