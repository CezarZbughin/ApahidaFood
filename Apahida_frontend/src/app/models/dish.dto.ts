import { ImageUrlDTO } from "./ImageUrlDTO";

export class DishDTO {

  id: number = 0;
  name: string = '';
  price: number = 0;
  stock: number = 0;
  images: ImageUrlDTO[] = [];

  constructor(id: number, name: string, price: number, stock: number, images : ImageUrlDTO[]) {
    this.id = id;
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.images = images;
  }

}
