package com.primeraparte.calculadoracalorias.repositories;

import com.primeraparte.calculadoracalorias.dtos.CaloriesDTO;
import com.primeraparte.calculadoracalorias.exceptionsHandler.IngredientNotFound;

public interface CaloriesRepository {
    CaloriesDTO findCalorieByIngredient(String ingredient) throws IngredientNotFound;
}
