import { ServerService } from '../serverService/server.service';
import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatSort } from '@angular/material';
import { SharedDataService } from '../sharedData/shared-data.service';
import { Server } from '../model/server';
import { animate, state, style, transition, trigger } from '@angular/animations';


@Component({
  selector: 'app-server-list',
  templateUrl: './server-list.component.html',
  styleUrls: ['./server-list.component.css'],
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({height: '0px', minHeight: '0'})),
      state('expanded', style({height: '*'})),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ],
})
export class ServerListComponent implements OnInit {
  server: Server[];

  dataSource: MatTableDataSource<Server>;
  displayedColumns = ['name', 'fullCapacity', 'storageReserved', 'storageFree', 'storageRatio', 'storageRam',
  'storageRamUsage', 'storageCpuUsage', 'storageDesc'];
  expandedElement: any;

  @ViewChild(MatSort, {static: true}) sort: MatSort;

  constructor(private service: ServerService, private sharedData: SharedDataService) {
  }

  ngOnInit() {
    this.service.getServers().subscribe(data => {
      this.server = data;
      this.dataSource = new MatTableDataSource(this.server);
      this.dataSource.sort = this.sort;
    });
  }
}
