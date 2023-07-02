import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { count } from 'rxjs';
import { OrderEntryDTO } from 'src/app/models/OrderEntryDTO';
import { DishDTO } from 'src/app/models/dish.dto';
import { DishListDTO } from 'src/app/models/dishListDTO';
import { OrderDTO } from 'src/app/models/orderDTO';
import { OrderEntryPlaceDTO } from 'src/app/models/orderEntryPlaceDTO';
import { OrderListDTO } from 'src/app/models/orderListDTO';
import { DataService } from 'src/app/service/data.service';
import { OrderPlaceDTO} from 'src/app/models/orderPlaceDTO'
import { WebMessageDTO } from 'src/app/models/WebMessage';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {environment} from '../../../environments/environment.development';

@Component({
  selector: 'app-home',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent {
  
  orders : OrderDTO[];
  entries : OrderEntryDTO[];
  allDishes : DishDTO[];

  constructor(
    private router: Router,
    private dataService : DataService,
    private http : HttpClient
  ) {  }

  ngOnInit(): void {
    this.dataService.getOrders().subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : OrderListDTO) => {
        this.orders = response.orders;
      }
    });

    this.dataService.getDishes().subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : DishListDTO) => {
        this.allDishes = response.dishes;
        console.log(response);
      }
    })
  }

  onStatusChange(e : Event, order : OrderDTO){
    const value : string = (e.target as HTMLInputElement).value;
    if(value == ""){ return; }
    order.status = value;
    
    this.dataService.updateOrderStatus(order, value).subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : OrderDTO) => {}
    });
  }

  onAddClick(_dish : string, _count : string){
    if(_dish=="" || _count ==""){return;}
    const dishId = Number(_dish);
    const count = Number(_count);
    let dish : DishDTO | null = null;
    for(let d of this.allDishes){
      if(d.id == dishId){
        dish = d; 
        break;
      }
    }
    if(dish==null) return;
    
    if(this.entries == undefined){
      this.entries = [];
    }
    this.entries.push(new OrderEntryDTO(-1, dish, count));
  }

  onPlaceClick(){
      if(this.entries.length == 0) {return};

      let placeEntries : OrderEntryPlaceDTO[] = [];
      for(let entry of this.entries){
        placeEntries.push(new OrderEntryPlaceDTO(entry.dish.id, entry.count));
      }
      let order : OrderPlaceDTO = new OrderPlaceDTO(placeEntries);
      this.dataService.placeOrder(order).subscribe({
        complete: () => {},
        error: (error) => { console.log(error)},
        next: (response : OrderDTO | WebMessageDTO) => {
          if('message' in response){
            console.log(response.message);
          } else  {
            this.orders.push(response);
          }
        }
      });

      this.entries = [];
  }

  onExportClick(formatCode : number){
    var format : string = formatCode == 0 ? "csv" : "xlsx";
    
    const options = { 
      responseType: 'blob' as 'json',
      headers: new HttpHeaders({
        'Authorization' : `${sessionStorage.getItem("auth")}`
      })
    };
    
    const url = environment.apiBasUrl + "/order/get/as-file?format=csv";

    this.http.get(url, options).subscribe(
        (response: any) =>{
            let dataType = response.type;
            let binaryData = [];
            binaryData.push(response);
            let downloadLink = document.createElement('a');
            downloadLink.href = window.URL.createObjectURL(new Blob(binaryData, {type: dataType}));
            downloadLink.setAttribute('download', "orders."+format);
            document.body.appendChild(downloadLink);
            downloadLink.click();
        }
    )
  }


  
}
