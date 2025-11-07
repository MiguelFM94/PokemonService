package com.mafloresm.springcloud.msvc.pokemon.mapper;

import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonRequest;
import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonResponse;
import com.mafloresm.springcloud.msvc.pokemon.entities.Pokemon;
import com.mafloresm.springcloud.msvc.pokemon.entities.TypePokemon;
import com.mafloresm.springcloud.msvc.pokemon.models.PokemonModel;
import com.mafloresm.springcloud.msvc.pokemon.models.PokemonTypeModel;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PokemonMapper {

    public Pokemon toEntity(PokemonRequest pokemonRequest, Set<TypePokemon> typesPokemon){
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonRequest.getName());
        pokemon.setTypes(typesPokemon);
        return  pokemon;
    }

    public PokemonResponse toResponse(Pokemon pokemon){
        PokemonResponse response = new PokemonResponse();
        response.setId(pokemon.getIdPokemon());
        response.setName(pokemon.getName());
        response.setTypes(pokemon.getTypes().stream().map(TypePokemon::getName).collect(Collectors.toSet()));
        return response;
    }
//    public PokemonTypeModel pokemonTpyeEntityToModel(TypePokemon entity){
//        PokemonTypeModel model = new PokemonTypeModel();
//        model.setId(entity.getId());
//        model.setNombre(entity.getType());
//        return  model;
//    }
//
//    public PokemonModel entityToPokemonModel(Pokemon entity){
//        PokemonModel pokemonModel = new PokemonModel();
//        pokemonModel.setId(entity.getIdPokemon());
//        pokemonModel.setNombre(entity.getName());
//        pokemonModel.setTypePokemon(pokemonTpyeEntityToModel(entity.getType()));
//        return  pokemonModel;
//    }


}
