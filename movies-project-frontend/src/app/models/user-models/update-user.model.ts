export class UpdateUser{
  private name:string;
  private email:string;
  private imageUrl:string;
  private bio:string;

  constructor(name:string, email:string, imageUrl:string, bio:string){
    this.name=name;
    this.email=email;
    this.imageUrl=imageUrl;
    this.bio=bio;
  }
}
