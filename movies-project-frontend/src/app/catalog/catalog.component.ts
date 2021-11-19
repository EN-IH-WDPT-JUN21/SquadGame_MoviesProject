import { FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';
import { faSearch } from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-catalog',
  templateUrl: './catalog.component.html',
  styleUrls: ['./catalog.component.css']
})
export class CatalogComponent implements OnInit {

  faSearch = faSearch;

  registerForm = this.fb.group({
    search: ["", Validators.required],
  })

 //Constructor
 constructor(private fb: FormBuilder) {}

  ngOnInit(): void {
  }

    //onSubmit
    onSubmit():void{
    
    }

}
