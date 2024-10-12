import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Notifications'
    },
    children: [
      {
        path: '',
        redirectTo: 'badges',
        pathMatch: 'full'
      },
      {
        path: 'alerts',
        loadComponent: () => import('./alerts/alerts.component').then(m => m.AlertsComponent),
        data: {
          title: 'Alerts'
        }
      }
    ]
  }
];
