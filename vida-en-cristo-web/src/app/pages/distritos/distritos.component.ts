import { Component, inject, OnInit, signal } from '@angular/core';
import { DistritoService } from '../../services/distrito.service';
import Distrito from '../../model/distrito';

@Component({
  selector: 'app-distritos',
  standalone: true,
  imports: [],
  templateUrl: './distritos.component.html',
  styleUrl: './distritos.component.scss',
})
export class DistritosComponent implements OnInit {
  distritoSerice = inject(DistritoService);
  listDistritos = signal<Distrito[]>([]);

  ngOnInit(): void {
    this.getDistritos();
  }

  getDistritos() {
    this.distritoSerice.getAll().subscribe({
      next: (distritos: any) => {
        this.listDistritos.set(distritos);
      },
    });
  }
}
