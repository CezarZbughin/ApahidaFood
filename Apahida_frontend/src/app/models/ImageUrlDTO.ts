export class ImageUrlDTO {
    id : number = 0;
    url : string = "";
    dish : number = 0;
  
    constructor(id : number, url : string, dish : number) {
      this.id = id;
      this.url = url;
      this.dish = dish;
    }
}
  