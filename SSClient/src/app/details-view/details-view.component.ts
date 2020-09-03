import { Volume } from './../model/volume';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { SharedDataService } from '../sharedData/shared-data.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-details-view',
  templateUrl: './details-view.component.html',
  styleUrls: ['./details-view.component.css']
})
export class DetailsViewComponent implements OnInit {
  volume: Volume;
  id: number;

  constructor(private sharedData: SharedDataService, private route: ActivatedRoute) {
    this.route.params.subscribe(params => console.log(params));
  }

  onEdit() {

  }

  ngOnInit() {
    this.sharedData.volume$.subscribe(
      data => {
        this.volume = data;
      }
    );
  }
}
