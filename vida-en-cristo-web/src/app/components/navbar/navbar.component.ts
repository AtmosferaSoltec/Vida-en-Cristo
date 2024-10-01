import { CommonModule } from '@angular/common';
import { Component, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    MatIconModule,
    MatButtonModule,
    MatTooltipModule,
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.scss',
})
export class NavbarComponent {
  isOpen = signal(false);
  listMenu = [
    {
      name: 'Distritos',
      icon: 'info',
      link: '/menu/distritos',
    },
    {
      name: 'Miembros',
      icon: 'people',
      link: '/menu/miembros',
    },
  ];

  toggle() {
    this.isOpen.set(!this.isOpen());
  }
}
