import { ServerService } from './../server.service';
import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Server } from '../model/server';


const dummyData: Server[] = [{ id: 1, name: 'CHWISRV01', fullCapacity: 100, storageReserved: 50, storageFree: 50, storageRatio: 50.00}];

@Component({
  selector: 'app-server-list',
  templateUrl: './server-list.component.html',
  styleUrls: ['./server-list.component.css']
})
export class ServerListComponent implements OnInit {
  servers: Server[];

  dataSource = dummyData;
  displayedColumns = ['actions', 'id', 'name', 'fullCapacity', 'storageReserved', 'storageFree', 'storageRatio'];

  constructor(private service: ServerService) {
   }

  ngOnInit() {
   /* this.service.getALL().subscribe(data => {
      this.servers = data;
    }); */
  }

}
