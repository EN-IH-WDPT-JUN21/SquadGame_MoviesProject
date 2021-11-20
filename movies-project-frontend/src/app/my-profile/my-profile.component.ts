import { UpdateUser } from './../models/user-models/update-user.model';
import { UserDetails } from './../models/user-models/user-details.model';
import { UsersService } from './../users.service';
import { Component, OnInit } from '@angular/core';
import { faUser, faEnvelope, faUserTag, faFileAlt, faPencilAlt } from '@fortawesome/free-solid-svg-icons';
import { FormBuilder, Validators } from '@angular/forms';
import { NameValidator } from '../validators/name-validator';

@Component({
  selector: 'app-my-profile',
  templateUrl: './my-profile.component.html',
  styleUrls: ['./my-profile.component.css']
})
export class MyProfileComponent implements OnInit {

  faUser = faUser;
  faEnvelope = faEnvelope;
  faUserTag = faUserTag;
  faFileAlt = faFileAlt;
  faPencilAlt = faPencilAlt;

  registerForm = this.fb.group({
    username: [{value: "ironh4ck3r", disabled: true}],
    email: ["ironh4ck3r@mail.com", [Validators.required, Validators.email]],
    name: ["John Titor", [Validators.required, NameValidator.onlyLettersValidator]],
    bio: []
  })


  constructor(private fb: FormBuilder, private usersService:UsersService) { }

  ngOnInit(): void {
    console.log("User has JWT token:",this.usersService.hasJWT());
    let userDetails:UserDetails;
    this.usersService.getUserDetails().subscribe(resp=>{
      userDetails=resp;
      this.registerForm.patchValue({username:userDetails.login});
      this.registerForm.patchValue({email:userDetails.email})
      this.registerForm.patchValue({name:userDetails.name})
      this.registerForm.patchValue({bio:userDetails.bio})
    })

  }

  onSubmit():void{
    let updateUser:UpdateUser = new UpdateUser(
      this.registerForm.value.username,
      this.registerForm.value.email,
     '',
     this.registerForm.value.bio
    )
    this.usersService.updateUser(updateUser);
  }

}
