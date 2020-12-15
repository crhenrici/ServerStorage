import { Component, OnInit } from '@angular/core';
import { ServerOverviewDTO } from '../model/serverOverviewDTO';
import { OverviewPieChartData } from '../OverviewPieChartData';
import { ServerService } from '../serverService/server.service';
import { SharedDataService } from '../sharedData/shared-data.service';

@Component({
  selector: 'app-overall-view',
  templateUrl: './overall-view.component.html',
  styleUrls: ['./overall-view.component.css']
})
export class OverallViewComponent implements OnInit {

  server: ServerOverviewDTO;

  single: any[];

  constructor(private service: ServerService, private shared: SharedDataService) { 
  }

  ngOnInit() {
    this.service.getOverview().subscribe(data => {
      this.server = data;
      this.sendDataToPieChart(this.server);

    });
  }

  sendDataToPieChart(serverDTO: ServerOverviewDTO) {
    this.shared.transmitServerDTO(serverDTO);
  }
}
