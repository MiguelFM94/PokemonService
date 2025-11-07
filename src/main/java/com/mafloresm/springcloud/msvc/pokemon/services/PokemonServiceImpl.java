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

import java.util.*;
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
//        List<Pokemon> pokemonList = pokemonRepository.findAll();
//        List<PokemonModel>pokemonModelList = new ArrayList<>();
//        pokemonList.forEach(pokemon -> {
//           pokemonModelList.add(new PokemonModel(
//                    pokemon.getIdPokemon(),
//                    pokemon.getName(),
//                    new PokemonTypeModel(pokemon.getType().getId(),pokemon.getType().getType()))
//            );
//        });
//    return pokemonModelList;
        return null;
    }


    @Override
    @Transactional(readOnly = true)
    public PokemonModel getById(Long id) {
//        Optional<Pokemon> pokemon = pokemonRepository.findById(id);
//        PokemonModel pokemonModel = new PokemonModel();
//        pokemonModel = pokemon.map(pokemonMapper::entityToPokemonModel).orElseThrow(()->
//         new PokemonException("Pokemon no existe", HttpStatus.NOT_FOUND.value()));
//        return  pokemonModel;
        return null;
    }



    @Override
    @Transactional
    public PokemonResponse savePokemon(PokemonRequest pokemonRequest) {

        System.out.println("pokemonRequest = " + pokemonRequest);
        pokemonRequest.getTypesId().forEach(e-> System.out.println("HOLAAAAA: "+ e));
        Set<TypePokemon> types = new HashSet<>(typePokemonRepository.findAllById(pokemonRequest.getTypesId()));
        System.out.println("PokemonServiceImpl.savePokemon");
        types.forEach(e-> System.out.println(e.getName()));
        Pokemon pokemonToSave = pokemonMapper.toEntity(pokemonRequest,types);
        //        Pokemon pokemon = new Pokemon();
        return pokemonMapper.toResponse(pokemonRepository.save(pokemonToSave));
    }

    @Override
    @Transactional(readOnly = true)
    public PokemonModel getByName(String nombre) {
//        Optional<Pokemon> pokemon = Optional.ofNullable(pokemonRepository.findByNameContainingIgnoreCase(nombre));
//        PokemonModel pokemonModel = pokemon.map(pokemonMapper::entityToPokemonModel).
//                orElseThrow(()->  new PokemonException("El Pokemon no existe", HttpStatus.NOT_FOUND.value()));
//
//        return pokemonModel;
        return null;
    }

    @Override
    public Optional<PokemonResponse> updatePokemon(PokemonRequest pokemonRequest) {
//        TypePokemon type = typePokemonRepository.findById(pokemonRequest.getTypePokemon().getId())
//                .orElseThrow(() -> new PokemonException("Tipo de pokemon no existe", HttpStatus.NOT_FOUND.value()));
//
//        Pokemon existing = pokemonRepository.findById(pokemonRequest.getId())
//                .orElseThrow(() -> new PokemonException("Pokemon no encontrado",  HttpStatus.NOT_FOUND.value()));
//        existing.setName(pokemonRequest.getNombre());
//        existing.setType(type);
//
//        Pokemon saved = pokemonRepository.save(existing);
//        return Optional.of(pokemonMapper.entityToResponse(saved));
        return null;
    }

    @Override
    public void deletePokemon(Long id) {

        Pokemon existing = pokemonRepository.findById(id).
                orElseThrow(()->new PokemonException("Pokemon no encontrado", HttpStatus.NOT_FOUND.value()));
        existing.setActive(false);
        pokemonRepository.save(existing);
    }
}
