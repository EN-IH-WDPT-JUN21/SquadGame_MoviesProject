import { LoginDetails } from './../models/user-models/login-details.model';
import { UserDetails } from './../models/user-models/user-details.model';
import { UsersService } from './../users.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { faLock, faUser, faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  //Variables
  faPasswordIcon = faEyeSlash;
  faLock = faLock;
  faUser = faUser;
  fieldTextType: boolean = false;
  authError=false;

  registerForm = this.fb.group({
    username: ["", Validators.required],
    password: ["", Validators.required],
  })

  //Constructor
  constructor(private fb: FormBuilder, private router: Router, private usersService:UsersService) {}

  //ngOnInit
  ngOnInit(): void {
  }

  // Handles Password show/hide toggle
  toggleFieldTextType() {

    //toggles password field type (text/password)
    this.fieldTextType = !this.fieldTextType;

    //toggles password show/hide icon
    if (this.fieldTextType){
      this.faPasswordIcon = faEye;
    }else {
      this.faPasswordIcon = faEyeSlash;
    }

  }

  //onSubmit
  async onSubmit(){

    //if login is correct, redirects to My Profile

    let loginDetails: LoginDetails = new LoginDetails(
      this.registerForm.value.username,
      this.registerForm.value.password
    )
    await this.usersService.login(loginDetails);

    if(this.usersService.hasJWT()) {
      this.router.navigate(['/my-profile']).then(
        ()=>{location.reload();}
      );
    }else{
      this.authError=true;
    }
  }
}
