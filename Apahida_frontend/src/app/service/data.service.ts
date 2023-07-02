import { Injectable } from '@angular/core';
import {JavaHttpService} from '../connection/http/java-http.service';
import {map, Observable} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {DishDTO} from '../models/dish.dto';
import { DishListDTO } from '../models/dishListDTO';
import { DishSaveDTO as DishSaveDTO } from '../models/dishSaveDTO';
import { OrderDTO } from '../models/orderDTO';
import { OrderListDTO } from '../models/orderListDTO';
import { OrderPlaceDTO } from '../models/orderPlaceDTO';
import { WebMessageDTO } from '../models/WebMessage';
import { UserDTO } from '../models/EndUserDTO';
import { ImageUrlDTO } from '../models/ImageUrlDTO';


@Injectable({
  providedIn: 'root'
})
export class DataService {
 
  constructor(
    private http: JavaHttpService,
    private router: Router,
    private route: ActivatedRoute
  ) { }

  getSelf() : Observable<UserDTO> {
    return this.http.get<UserDTO>('/user/self');
  }
  //dish
  getDishes(): Observable<DishListDTO> {
    return this.http.get<DishListDTO>('/dish/get');
  }

  updateDish(dish: DishDTO): Observable<DishDTO> {
    let img_urls : string[] = [];
    for(let img of dish.images){
      img_urls.push(img.url);
    }
    let body : DishSaveDTO = new DishSaveDTO(dish.name, dish.price, dish.stock, img_urls);
    return this.http.post<DishDTO, DishSaveDTO>('/dish/update/id=' + dish.id, body);
  }

  createDish(dish: DishSaveDTO) {
    return this.http.post<DishDTO, DishSaveDTO>('/dish/create', dish);
  }

  deleteDish(dish: DishDTO) {
    return this.http.delete<void>('/dish/delete/id=' + dish.id);
  }

  //order
  getOrders() : Observable<OrderListDTO>{
    return this.http.get<OrderListDTO>('/order/get');
  }

  updateOrderStatus(order : OrderDTO, status : string) : Observable<OrderDTO>{
    return this.http.post<OrderDTO, null>('/order/update/id=' + order.id + '/status=' + status, null);
  }

  placeOrder(body : OrderPlaceDTO) : Observable<OrderDTO | WebMessageDTO> {
    return this.http.post<OrderDTO, OrderPlaceDTO>('/order/place', body);
  }

  getOrdersAsFile() {
    return this.http.get<OrderListDTO>('/order/get/as-file');
  }
}
