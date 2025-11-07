package com.mafloresm.springcloud.msvc.pokemon.services;

import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonRequest;
import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonResponse;
import com.mafloresm.springcloud.msvc.pokemon.entities.Pokemon;
import com.mafloresm.springcloud.msvc.pokemon.models.PokemonModel;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface PokemonService {

    List<PokemonModel> findAll();
    PokemonModel getById(Long id);
    PokemonModel getByName(String nombre);
    PokemonResponse savePokemon(PokemonRequest pokemonRequest);
    Optional<PokemonResponse> updatePokemon(PokemonRequest pokemonRequest);
    void deletePokemon(Long id);
}
