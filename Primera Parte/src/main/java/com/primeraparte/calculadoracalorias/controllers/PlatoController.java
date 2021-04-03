package com.primeraparte.calculadoracalorias.controllers;

import com.primeraparte.calculadoracalorias.dtos.DishDTO;
import com.primeraparte.calculadoracalorias.dtos.DishResponseDTO;
import com.primeraparte.calculadoracalorias.dtos.ErrorDTO;
import com.primeraparte.calculadoracalorias.exceptionsHandler.IngredientNotFound;
import com.primeraparte.calculadoracalorias.services.DishService;
import com.primeraparte.calculadoracalorias.services.DishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlatoController {
    @Autowired
    private DishService dishService;

    @PostMapping("/calculate")
    public ResponseEntity<DishResponseDTO> calculate(@RequestBody DishDTO dish) throws IngredientNotFound {

        return ResponseEntity.ok(dishService.calculate(dish));
    }

    @PostMapping("/calculatelist")
    public ResponseEntity<List<DishResponseDTO> >calculateList(@RequestBody List<DishDTO> dishes) throws IngredientNotFound {
        return ResponseEntity.ok(dishService.calculateList(dishes));
    }

    // Excepcion
    @ExceptionHandler(IngredientNotFound.class)
    public ResponseEntity<ErrorDTO> handleException(IngredientNotFound e) {
        ErrorDTO errorDTO = new ErrorDTO("Ingrediente invalido", e.getMessage());
        return ResponseEntity.badRequest().body(errorDTO);
    }

}
