import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Usuarios'
    },
    children: [
      {
        path: '',
        redirectTo: 'colors',
        pathMatch: 'full'
      },
      // {
      //   path: 'lista',
      //   loadComponent: () => import('../base/clientes/usuarios.component').then(m => m.ClienteComponent),
      //   data: {
      //     title: 'usuarios'
      //   }
      // },
      {
        path: 'typography',
        loadComponent: () => import('./typography.component').then(m => m.TypographyComponent),
        data: {
          title: 'Typography'
        }
      },
      // {
      //   path: 'clientes',
      //   loadComponent: () => import('./clientes/cliente.component').then(m => m.FormClienteComponent),
      //   data: {
      //     title: 'clientes'
      //   }
      // },
    ]
  }
];

