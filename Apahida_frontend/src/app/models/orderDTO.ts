import { OrderEntryDTO } from "./OrderEntryDTO";
import { DatePipe } from '@angular/common';

export class OrderDTO {
    id : number;
    created : Date;
    status : string;
    entries : OrderEntryDTO[];
  
    constructor(id : number, created : Date, status : string, entries : OrderEntryDTO[] ) {
      this.id = id;
      this.created = created;
      this.status = status;
      this.entries = entries;
    }
}
  