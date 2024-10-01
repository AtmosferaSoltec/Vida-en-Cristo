import { Component, inject, OnInit, viewChild } from '@angular/core';
import Miembro from '../../model/miembro';
import { MiembroService } from '../../services/miembro.service';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatSort, MatSortModule } from '@angular/material/sort';

import { CommonModule } from '@angular/common';
import * as xls from 'xlsx';

@Component({
  selector: 'app-miembros',
  standalone: true,
  imports: [CommonModule, MatTableModule, MatPaginatorModule, MatSortModule],
  templateUrl: './miembros.component.html',
  styleUrl: './miembros.component.scss',
})
export class MiembrosComponent implements OnInit {
  displayedColumns: string[] = [
    'dni',
    'nombres',
    'apellidos',
    'celular',
    'direc',
    'distrito',
    'fecha_nac',
  ];

  private readonly _sort = viewChild.required<MatSort>(MatSort);
  private readonly _paginator = viewChild.required<MatPaginator>(MatPaginator);

  miembroService = inject(MiembroService);
  listMiembros = new MatTableDataSource<Miembro>([]);

  onChange(event: any) {
    this.leerExcel(event);
  }

  leerExcel(event: any) {
    const file = event.target.files[0];
    let fr = new FileReader();
    fr.readAsArrayBuffer(file);
    fr.onload = (e) => {
      const data = new Uint8Array(fr.result as ArrayBuffer);
      const workBook = xls.read(data, { type: 'array' });
      const sheetName = workBook.SheetNames[0];
      const workSheet = workBook.Sheets[sheetName];
      const excelData: any[] = xls.utils.sheet_to_json(workSheet);
      const listExcel = excelData.map((item: any) => {
        console.log(item.Fecha);
        const miembro: Miembro = {
          dni: item.DNI,
          nombres: item.Nombres,
          apellidos: item.Apellidos,
          celular: item.Celular,
          direc: item.Direccion,
          id_distrito: item.Distrito,
          fecha_nac: item.Fecha,
        };
        return miembro;
      });
      this.listMiembros.data = listExcel;
    };
  }

  ngOnInit(): void {
    this.listMiembros.paginator = this._paginator();
    this.listMiembros.sort = this._sort();
    this.getMiembros();
  }

  getMiembros() {
    this.miembroService.getAll().subscribe({
      next: (miembros: any) => {
        this.listMiembros.data = miembros;
      },
    });
  }

  send() {
    this.listMiembros.data.forEach((miembro) => {
      miembro.dni = miembro.dni?.toString();
      miembro.celular = miembro.celular?.toString();

      this.miembroService.create(miembro).subscribe({
        next: (response: any) => {
          console.log(response);
        },
      });
    });
  }
}
