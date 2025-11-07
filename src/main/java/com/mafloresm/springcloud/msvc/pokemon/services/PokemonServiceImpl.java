package com.mafloresm.springcloud.msvc.pokemon.services;

import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonRequest;
import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonResponse;
import com.mafloresm.springcloud.msvc.pokemon.entities.Pokemon;
import com.mafloresm.springcloud.msvc.pokemon.entities.TypePokemon;
import com.mafloresm.springcloud.msvc.pokemon.exceptions.PokemonException;
import com.mafloresm.springcloud.msvc.pokemon.mapper.PokemonMapper;
import com.mafloresm.springcloud.msvc.pokemon.models.PokemonModel;
import com.mafloresm.springcloud.msvc.pokemon.models.PokemonTypeModel;
import com.mafloresm.springcloud.msvc.pokemon.repositories.PokemonRepository;
import com.mafloresm.springcloud.msvc.pokemon.repositories.TypePokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

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
    public List<PokemonModel> findAll() {
        List<Pokemon> pokemonList = pokemonRepository.findAll();
        List<PokemonModel>pokemonModelList = new ArrayList<>();
        pokemonList.forEach(pokemon -> {
           pokemonModelList.add(new PokemonModel(
                    pokemon.getIdPokemon(),
                    pokemon.getName(),
                    new PokemonTypeModel(pokemon.getType().getId(),pokemon.getType().getType()))
            );
        });
    return pokemonModelList;
    }


    @Override
    @Transactional(readOnly = true)
    public PokemonModel getById(Long id) {
        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
        PokemonModel pokemonModel = new PokemonModel();
        pokemonModel = pokemon.map(pokemonMapper::entityToPokemonModel).orElseThrow(()->
         new PokemonException("Pokemon no existe", HttpStatus.NOT_FOUND.value()));
        return  pokemonModel;
    }



    @Override
    @Transactional
    public Optional<PokemonResponse> savePokemon(PokemonRequest pokemonRequest) {
        Pokemon pokemon = new Pokemon();
        Optional<PokemonResponse> response;
        Optional<TypePokemon> optionalTypePokemon= typePokemonRepository.findById(pokemonRequest.getTypePokemon().getId());
        if(optionalTypePokemon.isPresent()){
            pokemon = pokemonMapper.toEntity(pokemonRequest, optionalTypePokemon.get());
        }else{
            throw new PokemonException("Tipo de pokemon no existe", HttpStatus.NOT_FOUND.value());
        }
        response = Optional.ofNullable(pokemonMapper.entityToResponse(pokemonRepository.save(pokemon)));

        return response;
    }

    @Override
    @Transactional(readOnly = true)
    public PokemonModel getByName(String nombre) {
        Optional<Pokemon> pokemon = Optional.ofNullable(pokemonRepository.findByNameContainingIgnoreCase(nombre));
        PokemonModel pokemonModel = pokemon.map(pokemonMapper::entityToPokemonModel).
                orElseThrow(()->  new PokemonException("El Pokemon no existe", HttpStatus.NOT_FOUND.value()));

        return pokemonModel;
    }

    @Override
    public Optional<PokemonResponse> updatePokemon(PokemonRequest pokemonRequest) {
        TypePokemon type = typePokemonRepository.findById(pokemonRequest.getTypePokemon().getId())
                .orElseThrow(() -> new PokemonException("Tipo de pokemon no existe", HttpStatus.NOT_FOUND.value()));

        Pokemon existing = pokemonRepository.findById(pokemonRequest.getId())
                .orElseThrow(() -> new PokemonException("Pokemon no encontrado",  HttpStatus.NOT_FOUND.value()));
        existing.setName(pokemonRequest.getNombre());
        existing.setType(type);

        Pokemon saved = pokemonRepository.save(existing);
        return Optional.of(pokemonMapper.entityToResponse(saved));

    }

    @Override
    public void deletePokemon(Long id) {

        Pokemon existing = pokemonRepository.findById(id).
                orElseThrow(()->new PokemonException("Pokemon no encontrado", HttpStatus.NOT_FOUND.value()));
        existing.setActive(false);
        pokemonRepository.save(existing);
    }
}
