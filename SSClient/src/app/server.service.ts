import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Server } from './model/server';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
  providedIn: 'root'
})
export class ServerService {
  httpUrl: String;
  http: HttpClient;

  constructor() {
    this.httpUrl = 'http://localhost:8080';
   }

  getALL(): Observable<Server[]> {
    var url = `${this.httpUrl}/servers`;
    return this.http.get<Server[]>(url,httpOptions);
  }
}
