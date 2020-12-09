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
  data: any[];
  @Input()
  chartData: Volume;
  chartResult: VolumeChartData[] = [{ name: '', points: [{ x: 2, y: 2 }] }];

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
    console.log('chartData ', this.chartData);
    // Object.assign(this.chartData);
    this.chartResult[0].name = this.chartData.name;
    let index = 0;
    this.chartData.volumeHistories.forEach((vol) => {
      this.chartResult[0].points[index] = { x: 0, y: 0};
      this.chartResult[0].points[index].x = new Date(vol.date).getTime();
      this.chartResult[0].points[index].y = vol.storageRatio;
      index++;
    });
  }

  formatXAxisValue(value: number) {
    const date = new Date(value);
    return date;
  }

}

