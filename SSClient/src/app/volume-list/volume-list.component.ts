import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource } from '@angular/material';
import { Server } from '../model/server';
import { Volume } from '../model/volume';
import { ServerService } from '../serverService/server.service';
import { SharedDataService } from '../sharedData/shared-data.service';

@Component({
  selector: 'app-volume-list',
  templateUrl: './volume-list.component.html',
  styleUrls: ['./volume-list.component.css']
})
export class VolumeListComponent implements OnInit {
  volume: Volume[];
  @Input()
  server: Server;

  dataSource: MatTableDataSource<Volume>;
  displayedColumns = ['actions', 'name', 'fullCapacity', 'storageReserved', 'storageFree', 'storageRatio'];

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private service: ServerService, private sharedData: SharedDataService) {
  }

  ngOnInit() {
    console.log('Server for request: ', this.server);
    this.service.getVolumes(this.server).subscribe(data => {
      this.volume = data;
      console.log('Data: ', data);
      console.log('Volumes: ', this.volume);
      this.dataSource = new MatTableDataSource(this.volume);
      this.dataSource.sort = this.sort;
    });
  }

  sendDataToDetails(volume: Volume) {
    this.sharedData.transmitData(volume);
  }
}
