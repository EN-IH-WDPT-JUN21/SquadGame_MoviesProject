import { UpdateUser } from './models/user-models/update-user.model';
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
  private userId:number=-99;

  constructor(private http:HttpClient) {
    this.restoreData()
   }

  async login(loginDetails:LoginDetails){
    try{
      const response = await this.http.post(`${this.baseUrl}/users/login`,loginDetails,{headers:this.composeHeader(), observe:'response'}).toPromise();
      let tmpToken = response.headers.get('pragma');
      this.token = tmpToken? tmpToken : '';
      this.storeData();
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
  updateUser(updateUser:UpdateUser){
    this.http.put(`${this.baseUrl}/user_details`,updateUser,{headers:this.composeHeader()});
  }

  composeHeader(){
    return {
      'Content-Type':  'application/json',
      'Authorization': 'Bearer '+this.token
    }
  }
  getToken(){
    return this.token;
  }
  private storeData(){
    window.localStorage.clear();
    window.localStorage.setItem('token',this.token);
    window.localStorage.setItem('id',this.userId.toString());
  }
  private restoreData(){
    let tokenTmp: string | null;
    let userIdTmp: string | null;
    tokenTmp = window.localStorage.getItem('token');
    userIdTmp = window.localStorage.getItem('id');
    this.token = tokenTmp ? tokenTmp : '';
    this.userId = userIdTmp ? +userIdTmp : -99;
  }
  logout(){
    localStorage.clear();
    this.token='';
    console.log("localStorage after logut:",localStorage);
    console.log("JWT after logut:",this.token);
  }
  setId(id:number){
    this.userId=id;
  }
  getId():number{
    return this.userId;
  }
}
