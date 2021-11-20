export class RegisterDetails{
  private login:string;
  private email:string;
  private password:string;

  constructor(login:string,password:string,email:string){
    this.email=email;
    this.login=login;
    this.password=password;
  }
}
