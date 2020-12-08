import { Server } from './../model/server';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map} from 'rxjs/operators';
import { ServerOverviewDTO } from '../model/serverOverviewDTO';
import { Volume } from '../model/volume';

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

  public getServers(): Observable<any> {
    const url = `${this.httpUrl}/servers`;
    return this.http.get<Server[]>(url, httpOptions).pipe(
      map(res => res as Server[]),
      catchError(this.handleError)
    );
  }

  public getOverview(): Observable<any> {
    const url = `${this.httpUrl}/overview`;
    return this.http.get<ServerOverviewDTO>(url, httpOptions).pipe(
      map(res => res as ServerOverviewDTO),
      catchError(this.handleError)
    );
    }

  public getVolumes(server: Server): Observable<any> {
    const url = `${this.httpUrl}/volumes?server=${server}`;
    return this.http.get<Volume[]>(url, httpOptions).pipe(
      map(res =>  res as Volume[]),
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
