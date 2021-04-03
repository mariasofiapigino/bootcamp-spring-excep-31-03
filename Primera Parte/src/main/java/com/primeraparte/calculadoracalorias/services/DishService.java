package com.primeraparte.calculadoracalorias.services;

import com.primeraparte.calculadoracalorias.dtos.DishDTO;
import com.primeraparte.calculadoracalorias.dtos.DishResponseDTO;
import com.primeraparte.calculadoracalorias.exceptionsHandler.IngredientNotFound;

import java.util.List;

public interface DishService {
    DishResponseDTO calculate(DishDTO plato) throws IngredientNotFound;
    void calculateCalories(DishDTO dish, DishResponseDTO dishResponseDTO) throws IngredientNotFound;
    List<DishResponseDTO> calculateList(List<DishDTO> dishes) throws IngredientNotFound;
}
