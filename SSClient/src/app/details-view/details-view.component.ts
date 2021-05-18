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
    if (this.isLabelEdit || this.isServerEdit) {
      this.onSetEditFalse();
    }
    this.isDescEdit = true;
  }

  onLabelEdit() {
    if (this.isServerEdit || this.isDescEdit) {
      this.onSetEditFalse();
    }
    this.isLabelEdit = !this.isLabelEdit;
  }

  onServerEdit() {
    if (this.isDescEdit || this.isLabelEdit) {
      this.onSetEditFalse();
    }
    this.isServerEdit = !this.isServerEdit;
    this.isDescEdit = true;
  }

  onSetEditFalse() {
    this.isLabelEdit = false;
    this.isDescEdit = false;
    this.isServerEdit = false;
    this.onSave();
  }

  onSave() {
    console.log('Post request sent! With volume desc', this.volume.desc);
    console.log('Post request sent! With volume label', this.volume.mappingLabel);
    console.log('Post request sent! With volume server network desc', this.volume.server.networkDesc);
    this.service.postVolume(this.volume).subscribe(data => {
        console.log('Post request worked!');
    });
  }

  ngOnInit() {
    this.sharedData.volume$.subscribe(
      data => {
        this.volume = data;
      }
    );
  }
}
