package com.cezar.apahida.contoller;


import com.cezar.apahida.dto.DishDTO;
import com.cezar.apahida.dto.DishListDTO;
import com.cezar.apahida.model.Dish;
import com.cezar.apahida.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dish")
@CrossOrigin("*")
public class DishController {
    @Autowired
    private DishService dishService;

    @CacheEvict(value = "first", allEntries = true)
    @GetMapping("/get")
    public ResponseEntity<?> getAll(Principal principal){
        List<Dish> dishes = dishService.getAll();
        return ResponseEntity.ok(new DishListDTO(dishes));
    }

    @GetMapping("/get/id={id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        Optional<Dish> dish = dishService.getById(id);
        if(dish.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(dish);
    }

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody DishDTO dishDTO){
        Dish dish = dishService.create(dishDTO.getName(), dishDTO.getPrice(), dishDTO.getStock(), dishDTO.getImages());

        return ResponseEntity.ok(dish);
    }

    @PostMapping("/update/id={id}")
    public ResponseEntity<?> update(@RequestBody DishDTO dishDTO, @PathVariable long id){
        Dish dish = new Dish();
        dish.setId(id);
        dish.setName(dishDTO.getName());
        dish.setPrice(dishDTO.getPrice());
        dish.setStock(dishDTO.getStock());
        Dish newDish = dishService.save(dish);
        return ResponseEntity.ok(newDish);
    }

    @DeleteMapping("/delete/id={id}")
    public void delete(@PathVariable long id){
       dishService.deleteById(id);
    }
}
