package com.cezar.apahida.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Table(name = "orders")
public class Order extends IdentityModel<Long>{
    public static final String STATUS_PLACED = "PLACED";
    public static final String STATUS_IN_PREP = "IN_PREP";
    public static final String STATUS_DONE = "DONE";

    private Date created;
    private String status;

    @OneToMany(mappedBy="order", cascade = CascadeType.ALL)
    private List<OrderEntry> entries = new ArrayList<>();
}
