import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map} from 'rxjs/operators';
import { Server } from '../model/server';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
  providedIn: 'root'
})
export class ServerService {
  httpUrl: string;

  constructor(private http: HttpClient) {
    this.httpUrl = 'http://localhost:8080';
   }

  public getALL(): Observable<any> {
    const url = `${this.httpUrl}/servers`;
    return this.http.get<Server[]>(url, httpOptions).pipe(
      map(res => res as Server[]),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    console.error('Error message:', error.error.message);
    console.error('Error status:', error.status);
    console.error('Error body:', error.error);

    return throwError('Error.');
  }
}
