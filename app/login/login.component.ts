import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
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

  registerForm = this.fb.group({
    username: ["", Validators.required],
    password: ["", Validators.required],
  })

  //Constructor
  constructor(private fb: FormBuilder) {}

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
  onSubmit():void{}
}
