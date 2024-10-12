import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import { NgClass } from '@angular/common';
import { DocsExampleComponent } from '@docs-components/public-api';
import {
  AvatarComponent,
  BreadcrumbComponent,
  BreadcrumbItemComponent,
  BreadcrumbRouterComponent, ButtonDirective,
  CardBodyComponent,
  CardComponent,
  CardHeaderComponent,
  ColComponent,
  FormCheckComponent,
  FormCheckInputDirective, FormCheckLabelDirective,
  FormControlDirective,
  FormDirective,
  FormLabelDirective,
  FormSelectDirective, ProgressComponent,
  RowComponent, TableDirective,
  TextColorDirective
} from '@coreui/angular';
import {ClienteService} from "./cliente.service";
import {Cliente} from "./cliente";
import {IconDirective} from "@coreui/icons-angular";
import {FormsModule} from "@angular/forms";
import { Router } from "@angular/router";

@Component({
  selector: 'cliente-form',
  templateUrl: './formCliente.component.html',
  standalone: true,
  imports: [RowComponent, ColComponent, TextColorDirective, CardComponent, CardHeaderComponent,
    CardBodyComponent, DocsExampleComponent, BreadcrumbComponent, BreadcrumbItemComponent, NgClass,
    BreadcrumbRouterComponent, FormDirective, FormLabelDirective, FormControlDirective, FormSelectDirective,
    FormCheckComponent, FormCheckInputDirective, FormCheckLabelDirective, ButtonDirective, AvatarComponent, IconDirective, ProgressComponent, TableDirective, FormsModule]
})
export class FormClienteComponent implements OnInit {
  public items = <any>[];

  users: any;
  clientes: Cliente[] = [];

  constructor( private clienteService: ClienteService, private router: Router) {}

  ngOnInit(): void {

    this.clienteService.findAll().subscribe(clientes => this.clientes = clientes);

    this.items = [
      { label: 'Home', url: '/', attributes: { title: 'Clientes' } },
      { label: 'Library', url: '/' },
      { label: 'Data', url: '/dashboard/' },
      { label: 'CoreUI', url: '/' }
    ];

    setTimeout(() => {
      this.items = [
        { label: 'CoreUI', url: '/' },
        { label: 'Data', url: '/dashboard/' },
        { label: 'Library', url: '/' },
        { label: 'Home', url: '/', attributes: { title: 'Home' } }
      ];
    }, 5000);
   // this.loadClientes();
  }

  @Output() newClienteEvent = new EventEmitter();

  onSubmit(): void {
    this.newClienteEvent.emit(this.clientes);
    console.log(this.clientes);
    this.clienteService.setCliente(this.clientes);
    this.router.navigate(['/clientes/lista']);
  }

  registrarCliente(){
    this.clienteService.setCliente(this.clientes);
    this.router.navigate(['/clientes/lista']);
  }

}
