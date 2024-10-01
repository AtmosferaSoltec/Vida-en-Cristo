import { Routes } from '@angular/router';
import { MiembrosComponent } from './pages/miembros/miembros.component';
import { DistritosComponent } from './pages/distritos/distritos.component';
import { MenuComponent } from './pages/menu/menu.component';
import { LoginComponent } from './pages/login/login.component';

export const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'menu',
    component: MenuComponent,
    children: [
      {
        path: 'miembros',
        component: MiembrosComponent,
      },
      {
        path: 'distritos',
        component: DistritosComponent,
      },
    ],
  },
  {
    path: "**",
    redirectTo: "login"
  }
];
