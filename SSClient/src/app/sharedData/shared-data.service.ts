import { Injectable } from '@angular/core';
import { Server } from '../model/server';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  private servers = new BehaviorSubject<Server>(null);

  servers$ = this.servers.asObservable();
  constructor() { }

  transmitData(newServer: Server) {
    this.servers.next(newServer);
    console.log('Sent server: ', newServer);
  }


}
