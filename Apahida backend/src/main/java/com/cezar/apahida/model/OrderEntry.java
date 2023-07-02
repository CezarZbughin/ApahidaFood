package com.cezar.apahida.model;


import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode
@Entity
@Table(name = "order_entry")
public class OrderEntry {
    @ManyToOne
    @MapsId("order_id")
    @JsonManagedReference
    @JsonIdentityReference(alwaysAsId=true)
    @Id
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("dish_id")
    @Id
    private Dish dish;

    int count;
}
