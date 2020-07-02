import { Component, OnInit, OnDestroy } from '@angular/core';
import { SharedDataService } from '../sharedData/shared-data.service';
import { Server } from '../model/server';
import { ActivatedRoute } from '@angular/router';
import { ServerService } from '../serverService/server.service';

@Component({
  selector: 'app-details-view',
  templateUrl: './details-view.component.html',
  styleUrls: ['./details-view.component.css']
})
export class DetailsViewComponent implements OnInit {
  server: Server;
  id: number;

  constructor(private sharedData: SharedDataService, private route: ActivatedRoute, private service: ServerService) {
    this.route.params.subscribe(params => console.log(params));
  }

  ngOnInit() {
    this.sharedData.servers$.subscribe(
      data => {
        this.server = data;
      }
    );
  }
}
