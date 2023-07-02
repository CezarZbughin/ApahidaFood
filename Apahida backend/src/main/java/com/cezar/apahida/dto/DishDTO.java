package com.cezar.apahida.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DishDTO {
    private String name;
    private int price;
    private int stock;
    private List<String> images;
}
