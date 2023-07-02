package com.cezar.apahida.dto;

import com.cezar.apahida.model.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class DishListDTO {
    public List<Dish> dishes;
}
