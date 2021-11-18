import { Component, OnInit } from '@angular/core';
import { faUser, faEnvelope, faUserTag, faFileAlt, faPencilAlt } from '@fortawesome/free-solid-svg-icons';
import { FormBuilder, Validators } from '@angular/forms';

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

  //we can choose between showing the user's info on the placeholder or pre-filled (below this comment)
  //right now, it's on both sides
  registerForm = this.fb.group({
    username: [{value: "ironh4ck3r", disabled: true}, Validators.required],
    email: ["ironh4ck3r@mail.com", [Validators.required, Validators.email]],
    name: ["John Titor", Validators.required],
    bio: []
  })


  constructor(private fb: FormBuilder) { }

  ngOnInit(): void {
  }

  onSubmit():void{

  }

}
