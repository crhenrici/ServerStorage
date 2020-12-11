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
  chartResult: VolumeChartData[] = [{ name: '', series: [{ name: "2", value: 2 }] }];

  // options
  legend: boolean = true;
  showLabels: boolean = true;
  animations: boolean = true;
  xAxis: boolean = true;
  yAxis: boolean = true;
  showYAxisLabel: boolean = true;
  showXAxisLabel: boolean = true;
  xAxisLabel: string = 'Date';
  yAxisLabel: string = 'Storage Usage [%]';
  timeline: boolean = true;


  constructor(private sharedData: SharedDataService) {
  }

  ngOnInit() {
    console.log('chartData ', this.chartData);
    // Object.assign(this.chartData);
    this.chartResult[0].name = this.chartData.name;
    let index = 0;
    this.chartData.volumeHistories.forEach((vol) => {
      this.chartResult[0].series[index] = { name: "0", value: 0};
      this.chartResult[0].series[index].name = new Date(vol.date).toDateString();
      this.chartResult[0].series[index].value = vol.storageRatio;
      index++;
    });
  }

  formatXAxisValue(value: number) {
    const date = new Date(value);
    return date;
  }

}

