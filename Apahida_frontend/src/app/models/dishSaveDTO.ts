export class DishSaveDTO {
  name: string = '';
  price: number = 0;
  stock: number = 0;
  images: string[] = [];

  constructor(name: string, price: number, stock: number, images : string[]) {
    this.name = name;
    this.price = price;
    this.stock = stock;
    this.images = images;
  }

}
