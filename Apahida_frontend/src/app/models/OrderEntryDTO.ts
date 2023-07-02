import { DishDTO } from "./dish.dto";

export class OrderEntryDTO {
    order : number;
    dish : DishDTO;
    count : number;
  
    constructor(order: number, dish: DishDTO, count : number) {
        this.order = order;
        this.dish = dish;
        this.count = count;
    }  
}
  