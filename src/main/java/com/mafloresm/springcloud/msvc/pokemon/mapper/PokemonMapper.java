package com.mafloresm.springcloud.msvc.pokemon.mapper;

import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonRequest;
import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonResponse;
import com.mafloresm.springcloud.msvc.pokemon.entities.Pokemon;
import com.mafloresm.springcloud.msvc.pokemon.entities.TypePokemon;
import com.mafloresm.springcloud.msvc.pokemon.models.PokemonModel;
import com.mafloresm.springcloud.msvc.pokemon.models.TypePokemonModel;
import org.springframework.stereotype.Component;

import java.util.HashSet;
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

    public PokemonModel toModel(Pokemon pokemon){
        PokemonModel pokemonModel = new PokemonModel();
        pokemonModel.setIdPokemon(pokemon.getIdPokemon());
        pokemonModel.setName(pokemon.getName());
        pokemonModel.setTypePokemon(pokemon.getTypes().stream().map(this::entityTotypePokemonModel).collect(Collectors.toSet()));
        return pokemonModel;
    }

    public TypePokemonModel entityListToTypePokemonModelList(TypePokemon typePokemonEntity){
        TypePokemonModel model = new TypePokemonModel();
        model.setTypePokemonId(typePokemonEntity.getId());
        model.setName(typePokemonEntity.getName());
        return  model;
    }
//
    public TypePokemonModel entityTotypePokemonModel(TypePokemon typePokemon){
        TypePokemonModel model = new TypePokemonModel();
        model.setName(typePokemon.getName());
        model.setTypePokemonId(typePokemon.getId());
        return model;
    }


}
