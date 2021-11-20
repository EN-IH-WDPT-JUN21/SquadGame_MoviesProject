export class UserDetails{

  id:number;
  login:string;
  name:string;
  email:string;
  bio:string;

  constructor(httpResponse:any){
    this.id=httpResponse.id;
    this.login=httpResponse.login;
    this.name=httpResponse.name;
    this.email=httpResponse.email;
    this.bio=httpResponse.bio;
  }
}
