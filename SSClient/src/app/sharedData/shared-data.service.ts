import { Volume } from './../model/volume';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { ServerOverviewDTO } from '../model/serverOverviewDTO';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  private volume = new BehaviorSubject<Volume>(null);
  private serverDTO = new BehaviorSubject<ServerOverviewDTO>(null);

  volume$ = this.volume.asObservable();
  serverDTO$ = this.serverDTO.asObservable();
  constructor() { }

  transmitData(newVolume: Volume) {
    this.volume.next(newVolume);
    console.log('Sent server: ', newVolume);
  }

  transmitServerDTO(newServerDTO: ServerOverviewDTO) {
    this.serverDTO.next(newServerDTO);
  }


}
