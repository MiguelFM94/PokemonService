package com.mafloresm.springcloud.msvc.pokemon.controllers;

import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonRequest;
import com.mafloresm.springcloud.msvc.pokemon.dto.PokemonResponse;
import com.mafloresm.springcloud.msvc.pokemon.services.PokemonService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping()
public class PokemonController {
    @Autowired
    private PokemonService pokemonService;

    @GetMapping("api/pokemon/")
    public ResponseEntity<?> getAllpokemon(){
        PokemonResponse response = pokemonService.findAll();
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PostMapping("api/pokemon/")
    public ResponseEntity<?> savePokemon(@Valid @RequestBody PokemonRequest pokeRequest){

        System.out.println("PokemonController.savePokemon");
        return ResponseEntity.status(HttpStatus.CREATED).body(pokemonService.savePokemon(pokeRequest));
    }

    @GetMapping("api/pokemon/id/{pokemonId}")
    public ResponseEntity<?> getPokemonById(@PathVariable Long pokemonId){
        PokemonResponse response = pokemonService.getById(pokemonId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @GetMapping("api/pokemon/name/{name}")
    public ResponseEntity<?> getPokemonByName(@PathVariable String name){
        PokemonResponse response = pokemonService.getByName(name);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

    @PutMapping("api/pokemon/")
    public ResponseEntity<?> updatePokemon(@Valid @RequestBody PokemonRequest request){
        return ResponseEntity.status(HttpStatus.OK).body(pokemonService.updatePokemon(request));
    }

    @DeleteMapping("api/pokemon/id/{pokemonId}")
    public ResponseEntity<?> deletePokemonById(@PathVariable @NotNull Long pokemonId){
        PokemonResponse response = pokemonService.deletePokemon(pokemonId);
        return ResponseEntity.status(response.getStatus()).body(response);
    }

}
