import { Component, OnInit } from '@angular/core';
import { ServerOverviewDTO } from '../model/serverOverviewDTO';
import { ServerService } from '../serverService/server.service';

@Component({
  selector: 'app-overall-view',
  templateUrl: './overall-view.component.html',
  styleUrls: ['./overall-view.component.css']
})
export class OverallViewComponent implements OnInit {

  server: ServerOverviewDTO;

  constructor(private service: ServerService) { }

  ngOnInit() {
    this.service.getOverview().subscribe(data => {
      this.server = data;
    });
  }


}
