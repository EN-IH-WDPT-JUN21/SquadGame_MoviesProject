import { Observable } from 'rxjs';
import { RegisterDetails } from './models/user-models/register-details.model';
import { LoginDetails } from './models/user-models/login-details.model';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserDetails } from './models/user-models/user-details.model';
import { BrowserDynamicTestingModule } from '@angular/platform-browser-dynamic/testing';

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private readonly baseUrl = 'http://localhost:8000';
  private token:string='';
  private registerStatus=0;

  constructor(private http:HttpClient) { }

  async login(loginDetails:LoginDetails){
    try{
      const response = await this.http.post(`${this.baseUrl}/users/login`,loginDetails,{headers:this.composeHeader(), observe:'response'}).toPromise();
      let tmpToken = response.headers.get('pragma');
      this.token = tmpToken? tmpToken : '';
    }catch(e){
      console.log(e)
    }
  }
  hasJWT():boolean{
    return this.token!=''
  }
  async register(registerDetails:RegisterDetails){
    try{
      const response = await this.http.post<UserDetails>(`${this.baseUrl}/users`,registerDetails,{headers:this.composeHeader(), observe:'response'}).toPromise();
      this.registerStatus=response.status;
    }catch(e){
      console.log(e)
    }
  }
  getRegisterStatus():number{
    return this.registerStatus;
  }

  getUserDetails():Observable<any>{
    console.log("getUserDetails header:",this.composeHeader())
    console.log("getUserDetails token value:",this.token)
      return this.http.get(`${this.baseUrl}/user_details`,{headers:this.composeHeader()})
  }

  composeHeader(){
    return {
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+this.token
    }
  }
}
