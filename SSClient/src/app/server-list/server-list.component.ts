import { ServerService } from '../serverService/server.service';
import { Component, OnInit, AfterViewInit, ViewChild, AfterContentInit } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { Server } from '../model/server';
import { SharedDataService } from '../sharedData/shared-data.service';


@Component({
  selector: 'app-server-list',
  templateUrl: './server-list.component.html',
  styleUrls: ['./server-list.component.css']
})
export class ServerListComponent implements OnInit {
  servers: Server[];

  dataSource: MatTableDataSource<Server>;
  displayedColumns = ['actions', 'id', 'name', 'fullCapacity', 'storageReserved', 'storageFree', 'storageRatio'];

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private service: ServerService, private sharedData: SharedDataService) {
  }

  ngOnInit() {
    this.service.getALL().subscribe(data => {
      this.servers = data;
      console.log('Data: ', data);
      console.log('Servers: ', this.servers);
      this.dataSource = new MatTableDataSource(this.servers);
      this.dataSource.sort = this.sort;
    });
  }

  sendData(server: Server) {
    this.sharedData.transmitData(server);
  }

}
