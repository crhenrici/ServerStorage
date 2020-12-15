import { Component, Input, OnInit } from '@angular/core';
import { SharedDataService } from 'src/app/sharedData/shared-data.service';
import { ServerOverviewDTO } from '../../model/serverOverviewDTO';
import { OverviewPieChartData } from '../../OverviewPieChartData';

@Component({
  selector: 'app-pie-chart',
  templateUrl: './pie-chart.component.html',
  styleUrls: ['./pie-chart.component.css']
})
export class PieChartComponent implements OnInit {

  serverData: ServerOverviewDTO;
  pieData: OverviewPieChartData[] = [{ name: '', value: 0}];
  readonly STORAGE_USED_LABEL = "Storage used [GB]";
  readonly STORAGE_FREE_LABEL = "Storage free [GB]";

  view: any = [500, 500];
  gradient: boolean = true;
  showLegend: boolean = true;
  showLabels: boolean = true;
  isDoughnut: boolean = false;
  legendPosition: string = 'below';

  colorScheme = {
    domain: ['#5AA454', '#A10A28', '#C7B42C', '#AAAAAA']
  };

  constructor(private shared: SharedDataService) { }

  ngOnInit() {
    this.shared.serverDTO$.subscribe(data => {
      this.serverData = data;
      this.assignData();
    });

    
   
  }

  assignData() {
    if (this.serverData != null) {
      this.pieData[0] = { name: this.STORAGE_USED_LABEL, value: this.serverData.totalCapacityUsed };
      this.pieData[1] = { name: this.STORAGE_FREE_LABEL, value: this.serverData.totalCapacityFree };
    }
  }

}
