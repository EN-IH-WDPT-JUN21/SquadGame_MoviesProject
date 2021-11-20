import { RegisterDetails } from './../models/user-models/register-details.model';
import { UsersService } from './../users.service';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { faLock, faUser, faEyeSlash, faEye, faEnvelope } from '@fortawesome/free-solid-svg-icons';
import { PasswordsMustMatch } from '../validators/passwords-must-match';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

   //Variables
   faPasswordIcon = faEyeSlash;
   faPasswordIcon2 = faEyeSlash;
   faLock = faLock;
   faUser = faUser;
   faEnvelope = faEnvelope;
   fieldTextType: boolean = false;
   fieldTextType2: boolean = false;

   registerForm = this.fb.group({
     username: ["", Validators.required],
     email: ["", [Validators.required, Validators.email]],
     password: ["", Validators.required],
     passwordConfirmation: ["", Validators.required]
   }, {validator: PasswordsMustMatch('password', 'passwordConfirmation')});






  //Constructor
  constructor(private fb: FormBuilder, private router: Router, private usersService:UsersService) {}

  //ngOnInit
  ngOnInit(): void {
  }

  // Handles Password show/hide toggle on Password
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

// Handles Password show/hide toggle on Password Confirmation2
toggleFieldTextType2() {

  //toggles password field type (text/password)
  this.fieldTextType2 = !this.fieldTextType2;

  //toggles password show/hide icon
  if (this.fieldTextType2){
    this.faPasswordIcon2 = faEye;
  }else {
    this.faPasswordIcon2 = faEyeSlash;
  }

}

  //onSubmit
  async onSubmit(){
    let registerDetails:RegisterDetails = new RegisterDetails(
      this.registerForm.value.username,
      this.registerForm.value.password,
      this.registerForm.value.email
    )
    await this.usersService.register(registerDetails);
    let status:number=this.usersService.getRegisterStatus();
    //takes data from fields
    if(status==202) {
      this.router.navigate(['/login']);
    }else{
      window.alert("Failed to register!");
    }
  }

}
