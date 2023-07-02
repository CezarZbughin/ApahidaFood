import { DishDTO } from "./dish.dto";

export class OrderEntryPlaceDTO {
    id : number;
    count : number;
  
    constructor(id: number, count : number) {
        this.id = id;
        this.count = count;
    }  
}
  