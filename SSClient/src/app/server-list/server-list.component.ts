import { Volume } from './../model/volume';
import { ServerService } from '../serverService/server.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { SharedDataService } from '../sharedData/shared-data.service';
import { Server } from '../model/server';


@Component({
  selector: 'app-server-list',
  templateUrl: './server-list.component.html',
  styleUrls: ['./server-list.component.css']
})
export class ServerListComponent implements OnInit {
  server: Server[];

  dataSource: MatTableDataSource<Server>;
  displayedColumns = ['name', 'fullCapacity', 'storageReserved', 'storageFree', 'storageRatio'];

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private service: ServerService, private sharedData: SharedDataService) {
  }

  ngOnInit() {
    this.service.getALL().subscribe(data => {
      this.server = data;
      console.log('Data: ', data);
      console.log('Volumes: ', this.server);
      this.dataSource = new MatTableDataSource(this.server);
      this.dataSource.sort = this.sort;
    });
  }

  generatePDF() {

  }
}
