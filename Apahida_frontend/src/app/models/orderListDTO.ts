import { OrderDTO } from "./orderDTO";

export class OrderListDTO {
    orders : OrderDTO[]
  
    constructor(orders : OrderDTO[]) {
      this.orders = orders;
    }  
}
  