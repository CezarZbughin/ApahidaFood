package com.cezar.apahida.service;

import com.cezar.apahida.model.Dish;
import com.cezar.apahida.model.Order;
import com.cezar.apahida.model.OrderEntry;
import com.cezar.apahida.repository.DishRepository;
import com.cezar.apahida.repository.OrderRepository;
import com.cezar.apahida.service.exception.UnavailableDishException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DishRepository dishRepository;


    public List<Order> getByDate(Date start, Date end){
        return orderRepository.findAllByCreatedBetween(start, end);
    }

    public Optional<Order> getById(long id){
        return orderRepository.findById(id);
    }
    public Order placeOrder(List<OrderEntry> entries) throws UnavailableDishException {
        entries = removeDuplicates(entries);
        boolean isUnavailable = entries.stream().anyMatch(
                e -> e.getCount() > e.getDish().getStock()
        );
        if(isUnavailable){
            throw new UnavailableDishException();
        }

        Order order = new Order();
        order.setId(null);
        order.setCreated(new Date());
        for(OrderEntry entry : entries) {
            entry.setOrder(order);
        }
        order.setEntries(entries);
        order.setStatus(Order.STATUS_PLACED);
        return orderRepository.save(order);
    }

    public Order updateStatus(Order order, String status){
        order.setStatus(status);
        return orderRepository.save(order);
    }

    public Map<Dish, Integer> getStatistics(){
        Map<Dish, Integer> result = dishRepository
                .findAll()
                .stream()
                .collect(Collectors.toMap(order -> order, order-> 0));

        List<Order> orders = orderRepository.findAll();
        for(Order order : orders){
            for(OrderEntry entry : order.getEntries()){
                Integer count = result.get(entry.getDish());
                if(count == null){
                    result.put(entry.getDish(), entry.getCount());
                } else {
                    result.put(entry.getDish(), count + entry.getCount());
                }
            }
        }

        return result;
    }
    private List<OrderEntry> removeDuplicates(List<OrderEntry> entries){
        List<OrderEntry> result = new ArrayList<>();
        for(OrderEntry entry : entries){
            Optional<OrderEntry> duplicate = result.stream().filter(
                    e -> e.getDish().getId() .equals( entry.getDish().getId())
            ).findAny();

            if(duplicate.isPresent()){
                int count = duplicate.get().getCount() + entry.getCount();
                duplicate.get().setCount(count);
            } else {
                result.add(entry);
            }
        }
        return result;
    }

}
