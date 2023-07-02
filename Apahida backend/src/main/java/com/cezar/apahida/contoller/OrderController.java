package com.cezar.apahida.contoller;

import com.cezar.apahida.dto.OrderDTO;
import com.cezar.apahida.dto.OrderListDTO;
import com.cezar.apahida.model.Dish;
import com.cezar.apahida.model.Order;
import com.cezar.apahida.model.OrderEntry;
import com.cezar.apahida.service.DishService;
import com.cezar.apahida.service.DownloadResponse.DownloadResponseFactory;
import com.cezar.apahida.service.OrderService;
import com.cezar.apahida.service.exception.UnavailableDishException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Duration;
import java.util.*;


@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private DishService dishService;

    @GetMapping("/get")
    public ResponseEntity<?> get() {
        Date now = new Date();
        Date start = new Date(now.getTime() - Duration.ofDays(7).toMillis());
        List<Order> orders = orderService.getByDate(start, now);
        return ResponseEntity.ok(new OrderListDTO(orders));
    }

    @GetMapping("/get/start={startDate}")
    public ResponseEntity<?> get(@PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate) {
        List<Order> orders = orderService.getByDate(startDate, new Date());
        return ResponseEntity.ok(new OrderListDTO(orders));
    }

    @GetMapping("/get/start={startDate}/end={endDate}")
    public ResponseEntity<?> get(
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @PathVariable @DateTimeFormat(pattern = "yyyy-MM-dd") Date endDate) {
        List<Order> orders = orderService.getByDate(startDate, endDate);
        return ResponseEntity.ok(new OrderListDTO(orders));
    }

    @GetMapping("/get/as-file")
    public ResponseEntity<?> get_file(@RequestParam(defaultValue = "") String format) throws IOException {
        Date now = new Date();
        Date start = new Date(now.getTime() - Duration.ofDays(365).toMillis());
        List<Order> orders = orderService.getByDate(start, now);

        StringBuilder stringBuilder = new StringBuilder("");
        for(Order order : orders){
            stringBuilder.append(order.getId()).append(", ");
            stringBuilder.append(order.getStatus()).append(", ");
            stringBuilder.append(order.getCreated()).append(", ");
            for(OrderEntry entry : order.getEntries()){
                stringBuilder.append(entry.getCount()).append("x").append(entry.getDish().getName()).append(" ");
            }
            stringBuilder.append(",\n");
        }

        return DownloadResponseFactory.getResponse(format).get(stringBuilder.toString().getBytes());
    }


    @CacheEvict(value = "first", allEntries = true)
    @PostMapping("/place")
    public ResponseEntity<?> placeOrder(@RequestBody OrderDTO orderDTO) {
        List<OrderEntry> entries = new ArrayList<>();

        for(var entry : orderDTO.getDishes()){
            Optional<Dish> dish = dishService.getById(entry.id);
            if(dish.isEmpty()){
                return ResponseEntity.notFound().build();
            }
            entries.add(new OrderEntry(null, dish.get(), entry.count));
        }

        try {
            Order order = orderService.placeOrder(entries);
            return ResponseEntity.ok(order);
        } catch (UnavailableDishException e) {
            WebMessage message =  new WebMessage();
            message.setMessage("Not enough in stock.");
            return ResponseEntity.ok(message);
        }
    }

    @PostMapping("/update/id={id}/status={status}")
    public ResponseEntity<?> updateStatus(@PathVariable long id, @PathVariable String status) {
        Optional<Order> order = orderService.getById(id);
        if(order.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        Order updated = orderService.updateStatus(order.get(), status);
        return ResponseEntity.ok(updated);
    }


}
