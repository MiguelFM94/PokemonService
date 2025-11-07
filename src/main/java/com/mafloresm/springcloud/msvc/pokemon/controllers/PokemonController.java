package com.mafloresm.springcloud.msvc.pokemon.controllers;

import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonRequest;
import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonResponse;
import com.mafloresm.springcloud.msvc.pokemon.models.PokemonModel;
import com.mafloresm.springcloud.msvc.pokemon.services.PokemonServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;

@RestController
@RequestMapping()
public class PokemonController {
    @Autowired
    private PokemonServiceImpl pokemonService;

    @GetMapping("api/pokemon/")
    public ResponseEntity<?> getAllpokemon(){

        List<PokemonModel> pokemonModelList = pokemonService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(pokemonModelList);
    }

    @PostMapping("api/pokemon/")
    public ResponseEntity<?> savePokemon(@Valid  @RequestBody PokemonRequest pokeRequest){

        System.out.println("PokemonController.savePokemon");
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.savePokemon(pokeRequest));
    }


    @GetMapping("api/pokemon/id/{pokemonId}")
    public ResponseEntity<?> getPokemonById(@PathVariable Long pokemonId){
        PokemonModel model =pokemonService.getById(pokemonId);
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    @GetMapping("api/pokemon/name/{name}")
    public ResponseEntity<?> getPokemonByName(@PathVariable String name){

        PokemonModel model = pokemonService.getByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(model);
    }

    @PutMapping("api/pokemon/")
    public ResponseEntity<?> updatePokemon(@Valid @RequestBody PokemonRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.updatePokemon(request));
    }

    @DeleteMapping("api/pokemon/id/{pokemonId}")
    public ResponseEntity<?> deletePokemonById(@PathVariable Long pokemonId){
        pokemonService.deletePokemon(pokemonId);
        return ResponseEntity.noContent().build();
    }
}
