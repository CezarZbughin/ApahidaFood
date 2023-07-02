import { DishDTO } from "./dish.dto";

export class DishListDTO {
    dishes : DishDTO[]
  
    constructor(dishes : DishDTO[]) {
      this.dishes = dishes;
    }  
}
  