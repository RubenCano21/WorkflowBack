import {Component, OnInit} from '@angular/core';
import {UsuarioService} from "../service/usuario.service";
import {Usuario} from "../models/usuario";
import {CardComponent} from "@coreui/angular";

@Component({
  selector: 'app-usuario',
  standalone: true,
  imports: [CardComponent],
  templateUrl: './usuario.component.html',
  styleUrl: './usuario.component.scss'
})
export class UsuarioComponent implements OnInit{
  usuarios: Usuario[] = [];

  constructor(private service: UsuarioService) {

  }

  ngOnInit(): void {
    this.service.findAll().subscribe(usuarios => this.usuarios = usuarios)
  }


}
