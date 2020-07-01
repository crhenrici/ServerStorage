import { Component, OnInit } from '@angular/core';
import { MatTableDataSource } from '@angular/material';
import { Server } from '../model/server';
import { Sort } from '@angular/material';

@Component({
  selector: 'app-server-list',
  templateUrl: './server-list.component.html',
  styleUrls: ['./server-list.component.css']
})
export class ServerListComponent implements OnInit {
  servers: Server[];
  dataSource = new MatTableDataSource(this.servers);
  displayedColumns = ['actions', 'id', 'name', 'fullCapacity', 'storageReserved', 'storageFree', 'storageRatio'];

  constructor() { }

  ngOnInit() {

  }

}
