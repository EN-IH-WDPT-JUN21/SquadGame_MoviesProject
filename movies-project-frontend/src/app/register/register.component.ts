import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { faLock, faUser, faEyeSlash, faEye } from '@fortawesome/free-solid-svg-icons';

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
   fieldTextType: boolean = false;
   fieldTextType2: boolean = false;
 
   registerForm = this.fb.group({
     username: ["", Validators.required],
     email: ["", [Validators.required, Validators.email]],
     password: ["", Validators.required],
     passwordConfirmation: ["", Validators.required]
   })

  //Constructor
  constructor(private fb: FormBuilder, private router: Router) {}

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
  onSubmit():void{
    
    //if login is correct, redirects to My Profile
    if(true) {
      this.router.navigate(['/my-profile']);
    }
  }

}
