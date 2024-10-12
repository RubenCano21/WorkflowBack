import { Routes}  from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Prueba'
    },
    children: [
      {
        path: '',
        redirectTo: 'prueba',
        pathMatch: 'full'
      },
      {
        path: 'prueba',
        loadComponent: () => import('./component/usuario.component').then(m => m.UsuarioComponent),
        data: {
          title: 'usuarios'
        }
      }
    ]
  }
];
