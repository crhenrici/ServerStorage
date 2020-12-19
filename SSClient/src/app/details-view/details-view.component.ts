import { Volume } from './../model/volume';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { SharedDataService } from '../sharedData/shared-data.service';
import { ActivatedRoute } from '@angular/router';
import { ServerService } from '../serverService/server.service';

@Component({
  selector: 'app-details-view',
  templateUrl: './details-view.component.html',
  styleUrls: ['./details-view.component.css']
})
export class DetailsViewComponent implements OnInit {
  volume: Volume;
  id: number;
  isEdit: boolean = false;

  constructor(private sharedData: SharedDataService, private route: ActivatedRoute,
    private service: ServerService) {
    this.route.params.subscribe(params => console.log(params));
  }

  onEdit() {
    this.isEdit = !this.isEdit;
  }

  onSave() {
    console.log('Post request sent! With volume desc', this.volume.desc);
    this.service.postVolume(this.volume);
    this.isEdit = !this.isEdit;
  }

  ngOnInit() {
    this.sharedData.volume$.subscribe(
      data => {
        this.volume = data;
      }
    );
  }
}
