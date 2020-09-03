import { Volume } from './../model/volume';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map} from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({'Content-Type': 'application/json'})
};


@Injectable({
  providedIn: 'root'
})
export class ServerService {
  httpUrl: string;

  constructor(private http: HttpClient) {
    this.httpUrl = 'http://localhost:9010/service';
   }

  public getALL(): Observable<any> {
    const url = `${this.httpUrl}/servers`;
    return this.http.get<Volume[]>(url, httpOptions).pipe(
      map(res => res as Volume[]),
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
