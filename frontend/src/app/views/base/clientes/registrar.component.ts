import { AfterViewInit, Component, HostBinding, Input, OnInit, forwardRef } from '@angular/core';
import { NgClass } from '@angular/common';

import {
    TextColorDirective,
    CardComponent,
    CardHeaderComponent,
    CardBodyComponent,
    RowComponent,
    ColComponent,
    AvatarComponent, ProgressComponent, TableDirective
} from '@coreui/angular';
import {IconDirective} from "@coreui/icons-angular";
import {DashboardComponent} from "../../dashboard/dashboard.component";
import {Cliente} from "./cliente";
import {ClienteService} from "./cliente.service";
import {ClienteComponent} from "./cliente.component";

@Component({

    templateUrl: 'registrar.component.html',
    standalone: true,
    imports: [TextColorDirective, CardComponent,  DashboardComponent,
      CardHeaderComponent, CardBodyComponent, RowComponent, forwardRef(() => ThemeColorComponent),
      AvatarComponent, ColComponent, IconDirective, ProgressComponent, TableDirective, ClienteComponent]
})
export class RegistrarComponent implements OnInit, AfterViewInit {

  clientes: Cliente[] = [];

  constructor(private clienteService: ClienteService  ) {
  }


  ngOnInit(): void {
    this.clienteService.findAll().subscribe(clientes => this.clientes = clientes);
  }

  ngAfterViewInit(): void {
    //this.themeColors();
  }
}

@Component({
    selector: 'app-theme-color',
    template: `
    <c-col xl="2" md="4" sm="6" xs="12" class="my-4 ms-4">
      <div [ngClass]="colorClasses" style="padding-top: 75%;"></div>
      <ng-content></ng-content>
    </c-col>
  `,
    standalone: true,
    imports: [ColComponent, NgClass],
})
export class ThemeColorComponent implements OnInit {
  @Input() color = '';
  public colorClasses = {
    'theme-color w-75 rounded mb-3': true
  };

  @HostBinding('style.display') display = 'contents';

  ngOnInit(): void {
    this.colorClasses = {
      ...this.colorClasses,
      [`bg-${this.color}`]: !!this.color
    };
  }
}

