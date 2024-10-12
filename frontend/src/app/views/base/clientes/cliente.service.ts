import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable, of} from "rxjs";
import {Cliente} from "./cliente";

@Injectable({
  providedIn: 'root'
})
export class ClienteService {

  private clientes: Cliente[] = [
    {id:1, username:'Ruben', password: '1234', admin: true},
    {id:2, username:'Carol', password: '1234', admin: false},
    {id:3, username:'Max', password: '1234', admin: false},
    {id:4, username:'Lucas', password: '1234', admin: true}
  ];

  private url: string = 'http://localhost:8080/api/users';

  constructor() {}


  findAll(): Observable<Cliente[]> {
    return of(this.clientes);
  }

  setCliente(cliente: any){
    this.clientes.push(cliente);
  }

  // getClientes(): Observable<any> {
  //   return this.http.get(`${this.url}/clientes`);
  // }

}
