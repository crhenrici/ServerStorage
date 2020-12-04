import { Component, OnInit } from '@angular/core';
import { data } from '../mockdata';
import { Server } from '../model/server';
import { ServerService } from '../serverService/server.service';

@Component({
  selector: 'app-overall-view',
  templateUrl: './overall-view.component.html',
  styleUrls: ['./overall-view.component.css']
})
export class OverallViewComponent implements OnInit {

  server: Server;

  numberOfServers: number;

  constructor(private service: ServerService) { }

  ngOnInit() {
    this.service.getALL().subscribe(data => {
      const servers = data;
      this.numberOfServers = servers.length;
      this.countUpDetails(servers);
    });
  }

  countUpDetails(servers: Server[]) {
    this.server = servers[0];
    servers.forEach(data => {
      this.server.fullCapacity += data.fullCapacity;
      this.server.latestStorageReserved += data.latestStorageReserved;
      this.server.latestStorageFree += data.latestStorageFree;
    });
    this.server.latestStorageRatio = (this.server.latestStorageReserved / this.server.fullCapacity) * 100;
  }

}
