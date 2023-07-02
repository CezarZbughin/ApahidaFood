import {Component} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {DataService} from '../../service/data.service';
import {JavaHttpService} from "../../connection/http/java-http.service";
import { DishDTO } from 'src/app/models/dish.dto';
import { UserDTO } from 'src/app/models/EndUserDTO';
import { DishListDTO } from 'src/app/models/dishListDTO';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent {

  constructor(
    private dataService: DataService,
    private http: JavaHttpService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  onLoginClick(username: string, password : string){
    var headerValue = this.makeAuthHeader(username, password);
    sessionStorage.setItem("auth", headerValue);
    
    this.dataService.getSelf().subscribe({
      complete: () => {},
      error: (error) => { console.log(error)},
      next: (response : UserDTO) => {
        console.log(response);
        sessionStorage.setItem("user_role", response.role);
        sessionStorage.setItem("user_username", response.username);
        
        if(response.role == "USER"){
          this.router.navigate(['/user'],{ skipLocationChange: false });
        } else if(response.role == "ADMIN"){
          this.router.navigate(['/admin'],{ skipLocationChange: false });
        }

      }
    })
    
  }

  makeAuthHeader(username :string, password : string) : string {
    var username_password = username + ':' + password;
    var encoded = btoa(username_password);
    return "Basic " + encoded;
  }

  
}
