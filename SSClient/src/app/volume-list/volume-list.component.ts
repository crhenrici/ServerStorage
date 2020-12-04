import { Component, OnInit, ViewChild } from '@angular/core';
import { MatSort, MatTableDataSource } from '@angular/material';
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

  dataSource: MatTableDataSource<Volume>;
  displayedColumns = ['actions', 'name', 'fullCapacity', 'storageReserved', 'storageFree', 'storageRatio', 'parentServer'];

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private service: ServerService, private sharedData: SharedDataService) {
  }

  ngOnInit() {
    this.service.getALL().subscribe(data => {
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
