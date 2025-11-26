package com.mafloresm.springcloud.msvc.pokemon.controllers;


import com.mafloresm.springcloud.msvc.pokemon.dto.ErrorDTO;
import com.mafloresm.springcloud.msvc.pokemon.exceptions.PokemonException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorDTO> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e){
        Map<String,String> errors = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(fieldError ->
                errors.put(fieldError.getField(), fieldError.getDefaultMessage())
        );
        ErrorDTO error = new ErrorDTO("Validation Error",errors,400,LocalDateTime.now());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    ResponseEntity<ErrorDTO> requestHandlerException(IllegalArgumentException e) {
        System.out.println("ControllerAdvice.requestHandlerException");
        Map<String, String > details = new HashMap<>();
        details.put("Error", e.getLocalizedMessage());
        ErrorDTO error = new ErrorDTO(
                "Validation Error",
                details,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()) ;
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(PokemonException.class)
    ResponseEntity<ErrorDTO> pokemonExceptionHandler(PokemonException e){
        Map<String, String > details = new HashMap<>();
        details.put("Error", e.getMessage());
        ErrorDTO error = new ErrorDTO(
                "Error",
                details,
                e.getHttpStatus(),
                LocalDateTime.now()) ;
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
    @ExceptionHandler(NoResourceFoundException.class)
    ResponseEntity<ErrorDTO> handleMethodArgumentTypeMismatchException(NoResourceFoundException e) {
        ErrorDTO errorDTO = new ErrorDTO(
                "Error",
                Map.of("Error", e.getMessage()),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now());

        return ResponseEntity.status(errorDTO.getHttpStatus()).body(errorDTO);
    }


}
