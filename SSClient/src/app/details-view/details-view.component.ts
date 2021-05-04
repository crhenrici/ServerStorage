import { Volume } from './../model/volume';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { SharedDataService } from '../sharedData/shared-data.service';
import { ServerService } from '../serverService/server.service';

@Component({
  selector: 'app-details-view',
  templateUrl: './details-view.component.html',
  styleUrls: ['./details-view.component.css']
})
export class DetailsViewComponent implements OnInit {
  volume: Volume;
  id: number;
  isDescEdit: boolean = false;
  isLabelEdit: boolean = false;
  isServerEdit: boolean = false;

  constructor(private sharedData: SharedDataService, private service: ServerService) {
  }

  onDescEdit() {
    this.isDescEdit = !this.isDescEdit;
  }

  onLabelEdit() {
    this.isLabelEdit = !this.isLabelEdit;
  }

  onServerEdit() {
    this.isServerEdit = !this.isServerEdit;
  }

  onSave() {
    console.log('Post request sent! With volume desc', this.volume.desc);
    console.log('Post request sent! With volume label', this.volume.mappingLabel)
    console.log('Post request sent! With volume server network desc', this.volume.server.networkDesc);
    this.service.postVolume(this.volume).subscribe(data => {
        console.log('Post request worked!');
    });
    this.isDescEdit = false;
    this.isLabelEdit = false;
    this.isServerEdit = false;
  }

  ngOnInit() {
    this.sharedData.volume$.subscribe(
      data => {
        this.volume = data;
      }
    );
  }
}
