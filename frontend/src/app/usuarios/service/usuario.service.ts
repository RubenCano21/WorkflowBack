import { Injectable } from '@angular/core';
import {Usuario} from "../models/usuario";
import {Observable, of} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class UsuarioService {

  private usuarios: Usuario[] =[
    {
      id: 1,
      username: "ruben",
      password: "1234",
      admin: false
    },
    {
      id: 2,
      username: "juan",
      password: "1234",
      admin: true
    }
  ];


  constructor() { }

  findAll(): Observable<Usuario[]> {
    return of(this.usuarios);
  }



}
