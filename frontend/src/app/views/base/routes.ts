import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    data: {
      title: 'Base'
    },
    children: [
      {
        path: '',
        redirectTo: 'cards',
        pathMatch: 'full'
      },
      {
        path: 'accordion',
        loadComponent: () => import('./accordion/accordions.component').then(m => m.AccordionsComponent),
        data: {
          title: 'Accordion'
        }
      },
      {
        path: 'register',
        loadComponent: () => import('./clientes/formCliente.component').then(m => m.FormClienteComponent),
        data: {
          title: 'Clientes'
        }
      },
      {
        path: 'lista',
        loadComponent: () => import('./clientes/cliente.component').then(m => m.ClienteComponent),
        data: {
          title: 'Clientes'
        }
      },
      {
        path: 'cards',
        loadComponent: () => import('./cards/cards.component').then(m => m.CardsComponent),
        data: {
          title: 'Cards'
        }
      },
      {
        path: 'list-group',
        loadComponent: () => import('./list-groups/list-groups.component').then(m => m.ListGroupsComponent),
        data: {
          title: 'List Group'
        }
      },
      {
        path: 'pagination',
        loadComponent: () => import('./paginations/paginations.component').then(m => m.PaginationsComponent),
        data: {
          title: 'Pagination'
        }
      },
      {
        path: 'placeholder',
        loadComponent: () => import('./placeholders/placeholders.component').then(m => m.PlaceholdersComponent),
        data: {
          title: 'Placeholder'
        }
      },
      {
        path: 'tables',
        loadComponent: () => import('./tables/tables.component').then(m => m.TablesComponent),
        data: {
          title: 'Tables'
        }
      },
      {
        path: 'tabs',
        loadComponent: () => import('./tabs/tabs.component').then(m => m.AppTabsComponent),
        data: {
          title: 'Tabs'
        }
      }
    ]
  }
];


