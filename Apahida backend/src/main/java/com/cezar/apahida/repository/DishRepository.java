package com.cezar.apahida.repository;

import com.cezar.apahida.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DishRepository extends JpaRepository<Dish, Long> {
    //void deleteById(long id);
}
