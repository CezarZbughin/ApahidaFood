import {Component, OnInit, resolveForwardRef} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import { ImageUrlDTO } from 'src/app/models/ImageUrlDTO';
import { DishDTO } from 'src/app/models/dish.dto';
import { DishListDTO } from 'src/app/models/dishListDTO';
import { DishSaveDTO } from 'src/app/models/dishSaveDTO';
import { DataService } from 'src/app/service/data.service';

@Component({
  selector: 'app-home',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit{

  dishes : DishDTO[];
  title : string = "Hello love";
  images : string[] = [];

  constructor(
    private router: Router,
    private dataService : DataService,
  ) {  }

  ngOnInit(): void {
    this.dataService.getDishes().subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : DishListDTO) => {
        this.dishes = response.dishes;
        console.log(response);
      }
    })
  }

  onDishNameChange(e : Event, dish : DishDTO){
    const value : string = (e.target as HTMLInputElement).value;
    if(value == ""){ return; }
    dish.name = value;
    
    this.dataService.updateDish(dish).subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : DishDTO) => {}
    });
  }

  onDishPriceChange(e : Event, dish : DishDTO){
    const value : string = (e.target as HTMLInputElement).value;
    if(value == ""){ return; }
    dish.price = Number(value);

    this.dataService.updateDish(dish).subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : DishDTO) => {}
    });
  }

  onDishStockChange(e : Event, dish : DishDTO){
    const value : string = (e.target as HTMLInputElement).value;
    if(value == ""){ return; }
    dish.stock = Number(value);
    
    this.dataService.updateDish(dish).subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : DishDTO) => {}
    });
  }

  onSaveClick(name : string, price : string, stock : string){
    if(name=="" || price == "" || stock == "") { return; }
    let dish : DishSaveDTO = new DishSaveDTO(name, Number(price), Number(stock), this.images);
    this.dataService.createDish(dish).subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : DishDTO) => {
        this.dishes.push(response);
      }
    });
  }

  onDeleteClick(dish : DishDTO){
    const index : number = this.dishes.indexOf(dish);
    this.dishes.splice(index, 1);
    this.dataService.deleteDish(dish).subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: () => {}
    });
  }

  onAddImageClick(url : string){
    this.images.push(url);
  }
}
