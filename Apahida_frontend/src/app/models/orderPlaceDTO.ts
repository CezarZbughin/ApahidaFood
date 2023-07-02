import { DishDTO } from "./dish.dto";
import { OrderEntryPlaceDTO } from "./orderEntryPlaceDTO";

export class OrderPlaceDTO {
    dishes : OrderEntryPlaceDTO[];
  
    constructor(dishes: OrderEntryPlaceDTO[]) {
        this.dishes = dishes;
    }  
}
  