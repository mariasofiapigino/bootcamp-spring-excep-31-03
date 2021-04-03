package com.primeraparte.calculadoracalorias.dtos;

import lombok.Data;

import java.util.List;

@Data
public class DishDTO {
    private String name;
    private List<IngredientDTO> ingredients;
}
