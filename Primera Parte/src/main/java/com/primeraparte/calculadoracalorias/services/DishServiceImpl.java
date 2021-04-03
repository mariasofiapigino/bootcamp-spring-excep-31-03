package com.primeraparte.calculadoracalorias.services;

import com.primeraparte.calculadoracalorias.dtos.*;
import com.primeraparte.calculadoracalorias.exceptionsHandler.IngredientNotFound;
import com.primeraparte.calculadoracalorias.repositories.CaloriesRepository;
import com.primeraparte.calculadoracalorias.repositories.CaloriesRepositoryImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishServiceImpl implements DishService{
    private final CaloriesRepository caloriesRepository;

    public DishServiceImpl(CaloriesRepository caloriesRepository) {
        this.caloriesRepository = caloriesRepository;
    }

    public DishResponseDTO calculate(DishDTO plato) throws IngredientNotFound {
        DishResponseDTO dishResponseDTO = new DishResponseDTO();
        calculateCalories(plato, dishResponseDTO);
        return dishResponseDTO;
    }

    public void calculateCalories(DishDTO dish, DishResponseDTO dishResponseDTO) throws IngredientNotFound {
        Double total = 0.0;
        Double max = 0.0;
        Double calorie;
        CaloriesDTO maxCalories = null;
        List<CaloriesDTO> ingredientsResponse = new ArrayList<>();
        for (IngredientDTO ingredient:dish.getIngredients()) {
            CaloriesDTO calories = caloriesRepository.findCalorieByIngredient(ingredient.getName());
            calorie = (calories.getCalories() * ingredient.getWeight())/100;
            total += calorie;

            CaloriesDTO caloriesDish = new CaloriesDTO(ingredient.getName(), calorie);
            ingredientsResponse.add(caloriesDish);

            if (max < caloriesDish.getCalories()) {
                max = caloriesDish.getCalories();
                maxCalories = caloriesDish;
            }
        }
        dishResponseDTO.setTotalCalories(total);
        dishResponseDTO.setIngredients(ingredientsResponse);
        dishResponseDTO.setIngredientMax(maxCalories);
    }

    public List<DishResponseDTO> calculateList(List<DishDTO> dishes) throws IngredientNotFound {
        List<DishResponseDTO> dishListResponse = new ArrayList<>();
        for (int i = 0; i < dishes.size(); i++) {
            dishListResponse.add(new DishResponseDTO());
            calculateCalories(dishes.get(i), dishListResponse.get(i));
        }
        return dishListResponse;
    }
}
