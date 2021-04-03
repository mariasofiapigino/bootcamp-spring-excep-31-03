package com.primeraparte.calculadoracalorias.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CaloriesDTO {
    private String name;
    private Double calories;
}
