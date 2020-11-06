import { Component, OnInit } from '@angular/core';
import { data } from '../mockdata';
import { VolumeChartData } from '../VolumeChartData';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  data:any[];
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


  constructor() {
  }

  ngOnInit() {
    Object.assign(this, { data });
    data.forEach((vol, i) => {
      this.chartResult[i].name = vol.name;
      vol.series.forEach((point, index) => {
        this.chartResult[i].points[index] = {x: 0, y: 0};
        this.chartResult[i].points[index].x = index * 2 + 5;
        this.chartResult[i].points[index].y = point.storageCapacity;
      });
    });
  }

  formatXAxisValue(value: number) {
    return `Value ${value}`;
}

}

