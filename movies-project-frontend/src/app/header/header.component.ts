import { UserDetails } from './../models/user-models/user-details.model';
import { UsersService } from './../users.service';
import { Component, OnInit } from '@angular/core';
import {faUser} from '@fortawesome/free-solid-svg-icons';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  navbarOpen = false;
  faUser = faUser;
  username='';


  constructor(private router: Router,private usersService:UsersService) { }

  ngOnInit(): void {
    if (this.showRoutesForLogged()){
      let userDetails:UserDetails;
      this.usersService.getUserDetails().subscribe(resp=>{
        userDetails = resp;
        this.username = userDetails.login;
      });
    }
  }

  toggleNavbar() {
    this.navbarOpen = !this.navbarOpen;
  }

  onLogout(){
    this.usersService.logout();
    this.router.navigate(['/login']).then(
      ()=>{location.reload();}
    );
  }

  showRoutesForLogged():boolean{
    return this.usersService.hasJWT();
  }
  hideFromLoggedIn():boolean{
    return !this.usersService.hasJWT();
  }
}
