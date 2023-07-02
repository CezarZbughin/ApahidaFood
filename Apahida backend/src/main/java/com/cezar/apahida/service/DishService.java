package com.cezar.apahida.service;

import com.cezar.apahida.model.Dish;
import com.cezar.apahida.model.ImageURL;
import com.cezar.apahida.repository.DishRepository;
import com.cezar.apahida.repository.ImageURLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService {
    @Autowired
    private DishRepository dishRepository;

    @Autowired
    private ImageURLRepository imageURLRepository;

    public List<Dish> getAll(){
        return dishRepository.findAll();
    }

    public Optional<Dish> getById(long id){
        return dishRepository.findById(id);
    }
    public Dish create(String name, int price, int stock, List<String> images){
        Dish dish = new Dish();
        dish.setId(null);
        dish.setName(name);
        dish.setPrice(price);
        dish.setStock(stock);
        dish.setImages(new ArrayList<>());

        for(String url : images){
            ImageURL imageURL = new ImageURL();
            imageURL.setId(null);
            imageURL.setUrl(url);
            imageURL.setDish(dish);
            //imageURLRepository.save(imageURL);
            dish.getImages().add(imageURL);
        }

        return dishRepository.save(dish);
    }

    public Dish save(Dish dish){
        return dishRepository.save(dish);
    }

    public void delete(Dish dish){
        dishRepository.delete(dish);
    }

    public void deleteById(long id){
        dishRepository.deleteById(id);
    }
}
