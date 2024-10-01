import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { BASE_URL } from '../config/contants';

@Injectable({
  providedIn: 'root',
})
export class DistritoService {
  url = `${BASE_URL}/distrito`
  http = inject(HttpClient);

  constructor() {}

  getAll() {
    return this.http.get(this.url);
  }
}
