package com.cezar.apahida.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Setter
@Getter
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "url")
public class ImageURL extends IdentityModel<Long>{
    private String url;

    @ManyToOne
    @JsonManagedReference
    @JsonIdentityReference(alwaysAsId=true)
    private Dish dish;
}
