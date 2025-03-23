import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private http: HttpClient) {}

  // Método para fazer a requisição GET
  getData(): Observable<string> {
    return this.http.get('/api/endpoint', { responseType: 'text' });
  }
}