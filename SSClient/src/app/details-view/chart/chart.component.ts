import { Component, Input, OnInit } from '@angular/core';
import { Volume } from 'src/app/model/volume';
import { SharedDataService } from 'src/app/sharedData/shared-data.service';
import { VolumeChartData } from '../../VolumeChartData';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  data:any[];
  chartData: Volume[];
  chartResult: VolumeChartData[] = [{name: '', points: [{ x: 2, y: 2}]}];

  view: any[] = [700, 300];

  // options
  legend: boolean = true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Year';
  yAxisLabel: string = 'Population';
  timeline: boolean = true;


  constructor(private sharedData: SharedDataService) {
  }

  ngOnInit() {
    this.sharedData.volumes$.subscribe(
      data => {
        this.chartData = data;
      }
    );
    console.log('chartData ', this.chartData);
   // Object.assign(this.chartData);
    this.chartData.forEach((vol, i) => {
      this.chartResult[i].name = vol.name;
      let index = 0;
      vol.volumeHistories.forEach((point) => {
        this.chartResult[i].points[index] = {x: 0, y: 0};
        this.chartResult[i].points[index].x = point.date.getDate();
        this.chartResult[i].points[index].y = point.storageRatio;
        index++;
      });
    });
  }

  formatXAxisValue(value: number) {
    let date = new Date();
    return date.setDate(value);
}

}

