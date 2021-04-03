package com.primeraparte.calculadoracalorias.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DishResponseDTO {
    private Double totalCalories;
    private List<CaloriesDTO> ingredients;
    private CaloriesDTO ingredientMax;
}
