package com.mafloresm.springcloud.msvc.pokemon.services;

import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonRequest;
import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonResponse;
import com.mafloresm.springcloud.msvc.pokemon.entities.Pokemon;
import com.mafloresm.springcloud.msvc.pokemon.entities.TypePokemon;
import com.mafloresm.springcloud.msvc.pokemon.exceptions.PokemonException;
import com.mafloresm.springcloud.msvc.pokemon.mapper.PokemonMapper;
import com.mafloresm.springcloud.msvc.pokemon.models.PokemonModel;
import com.mafloresm.springcloud.msvc.pokemon.repositories.PokemonRepository;
import com.mafloresm.springcloud.msvc.pokemon.repositories.TypePokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService{

    @Autowired
    private PokemonRepository pokemonRepository;

    @Autowired
    private TypePokemonRepository typePokemonRepository;

    @Autowired
    private PokemonMapper pokemonMapper;

    @Override
    @Transactional(readOnly = true)
    public PokemonResponse findAll() {
        Set<Pokemon> setPokemon = new HashSet<>(pokemonRepository.findAll());
        PokemonResponse pokemonResponse = new PokemonResponse();
        pokemonResponse.setData(setPokemon.stream().map(pokemonMapper::toModel).toList());
        pokemonResponse.setMessage("SUCCESS");
        pokemonResponse.setStatus(HttpStatus.OK);
        return pokemonResponse;
    }





    @Override
    @Transactional
    public PokemonResponse savePokemon(PokemonRequest pokemonRequest) {
        Set<TypePokemon> types = new HashSet<>(typePokemonRepository.findAllById(pokemonRequest.getTypesId()));
        PokemonModel model = Optional.of(pokemonRequest)
                .map(request -> pokemonMapper.toEntity(request, types))
                .map(pokemonRepository::save)
                .map(pokemonMapper::toModel).orElseThrow(()->new PokemonException("Error to save Pokemon", HttpStatus.INTERNAL_SERVER_ERROR.value()));
        return new PokemonResponse(List.of(model),HttpStatus.CREATED,"POKEMON CREATED");
    }

    @Override
    @Transactional(readOnly = true)
    public PokemonResponse getById(Long id) {

        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        PokemonModel pokemonModel = pokemon.map(pokemonMapper::toModel).orElseThrow(()->
                new PokemonException("Pokemon no existe", HttpStatus.NOT_FOUND.value()));
        return  new PokemonResponse(List.of(pokemonModel),HttpStatus.FOUND, "Pokemon Found");
    }

    @Override
    @Transactional(readOnly = true)
    public PokemonResponse getByName(String name) {
        Optional<Pokemon> pokemon = Optional.ofNullable(pokemonRepository.findByNameContainingIgnoreCase(name));
        PokemonModel pokemonModel = pokemon.map(pokemonMapper::toModel).
                orElseThrow(()->  new PokemonException("El Pokemon no existe", HttpStatus.NOT_FOUND.value()));

        return new PokemonResponse(List.of(pokemonModel),HttpStatus.FOUND,"Pokemon Found");
    }

    @Override
    public PokemonResponse updatePokemon(PokemonRequest pokemonRequest) {
        final Set<TypePokemon>typePokemons= new HashSet<>(typePokemonRepository.findAllById(pokemonRequest.getTypesId()));
//        TypePokemon type = typePokemonRepository.findById(pokemonRequest.getTypePokemon().getId())
//                .orElseThrow(() -> new PokemonException("Tipo de pokemon no existe", HttpStatus.NOT_FOUND.value()));

        final Pokemon existing = pokemonRepository.findById(pokemonRequest.getId())
                .orElseThrow(() -> new PokemonException("Pokemon no encontrado",  HttpStatus.NOT_FOUND.value()));
        existing.setName(pokemonRequest.getName());
        existing.setTypes(typePokemons);
        final List<PokemonModel> pokemonModels = List.of(pokemonMapper.toModel(pokemonRepository.save(existing)));
        return new PokemonResponse(pokemonModels,HttpStatus.OK,"Pokemon Modified");
    }

    @Override
    public PokemonResponse deletePokemon(Long id) {

        Pokemon existing = pokemonRepository.findById(id).
                orElseThrow(()->new PokemonException("Pokemon no encontrado", HttpStatus.NOT_FOUND.value()));
        existing.setActive(false);
        pokemonRepository.save(existing);
        return new PokemonResponse(List.of(),HttpStatus.OK,"Pokemon Deleted");
    }
}
