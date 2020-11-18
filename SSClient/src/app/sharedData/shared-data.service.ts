import { Volume } from './../model/volume';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedDataService {

  private volume = new BehaviorSubject<Volume>(null);
  private volumes = new BehaviorSubject<Volume[]>(null);

  volume$ = this.volume.asObservable();
  volumes$ = this.volumes.asObservable();
  constructor() { }

  transmitData(newVolume: Volume) {
    this.volume.next(newVolume);
    console.log('Sent server: ', newVolume);
  }

  transmitDataToChart(volumes: Volume[]) {
    this.volumes.next(volumes);
  }


}
