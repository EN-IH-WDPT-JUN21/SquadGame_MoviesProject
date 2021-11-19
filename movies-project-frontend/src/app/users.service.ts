import { LoginDetails } from './models/user-models/login-details.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private readonly baseUrl = 'http://localhost:8000';  // url for playlist API to review
  private headers = {
      'Content-Type':  'application/json'
    }

  constructor(private http:HttpClient) { }

  login(loginDetails:LoginDetails): number {
    let responseStatus=-999;
    console.log(loginDetails);
    this.http.post(`${this.baseUrl}/users/login`,loginDetails,{headers:this.headers, observe:'response'}).subscribe(resp=>{responseStatus=resp.status});
    console.log(responseStatus);
    return responseStatus;
    //return this.http.get(`${this.baseUrl}`,{'headers': this.httpOptions.headers});
  }
}
