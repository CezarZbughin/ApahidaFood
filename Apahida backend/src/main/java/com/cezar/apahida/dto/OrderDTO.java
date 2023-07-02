package com.cezar.apahida.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    public static class OrderEntryDTO{
        public long id;
        public int count;

    }
    private List<OrderEntryDTO> dishes;


}
