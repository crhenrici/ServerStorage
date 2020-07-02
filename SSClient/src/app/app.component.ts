import { Component } from '@angular/core';
import { SharedDataService } from './sharedData/shared-data.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  providers: [SharedDataService]
})
export class AppComponent {
  title = 'SSClient';
}
