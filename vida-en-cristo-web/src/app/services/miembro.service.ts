import { inject, Injectable } from '@angular/core';
import { BASE_URL } from '../config/contants';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class MiembroService {
  url = `${BASE_URL}/miembro`;
  http = inject(HttpClient);
  constructor() {}
  getAll() {
    return this.http.get(this.url);
  }

  create(data: any) {
    return this.http.post(this.url, data);
  }
}
