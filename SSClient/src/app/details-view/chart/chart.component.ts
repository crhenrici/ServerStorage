import { Component, Input, OnInit } from '@angular/core';
import { Volume } from 'src/app/model/volume';
import { VolumeChartData } from '../../VolumeChartData';

@Component({
  selector: 'app-chart',
  templateUrl: './chart.component.html',
  styleUrls: ['./chart.component.css']
})
export class ChartComponent implements OnInit {
  data: any[];
  @Input()
  chartData: Volume;
  chartResult: VolumeChartData[] = [{ name: '', series: [{ name: "2", value: 2 }] }];

  view: any[] = [700, 400];

  // options
  legend: boolean = true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Date';
  yAxisLabel: string = 'Free Capacity [%]';
  timeline: boolean = true;


  constructor() {
  }

  ngOnInit() {
    this.chartResult[0].name = this.chartData.name;
    let index = 0;
    var volumeHistories = Array.from(this.chartData.volumeHistories).sort((a,b) => {
      return new Date(a.date).getTime() - new Date(b.date).getTime();
    });
    this.chartData.volumeHistories.forEach(() => {
      this.chartResult[0].series[index] = { name: "0", value: 0};
      this.chartResult[0].series[index].name = new Date(volumeHistories[index].date).toDateString();
      this.chartResult[0].series[index].value = volumeHistories[index].storageRatio;
      index++;
    });
  }
}

