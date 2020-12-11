import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServerListComponent } from './server-list/server-list.component';
import { MatTableModule, MatDialogModule, MatSortModule, MatFormFieldModule, MatButtonModule, MatDividerModule, MatToolbarModule } from '@angular/material';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { DetailsViewComponent } from './details-view/details-view.component';
import { HttpClientModule } from '@angular/common/http';
import { ChartComponent } from './details-view/chart/chart.component';
import { VolumeListComponent } from './volume-list/volume-list.component';
import { OverallViewComponent } from './overall-view/overall-view.component';
import { NgxChartsModule } from '@swimlane/ngx-charts';

@NgModule({
  declarations: [
    AppComponent,
    ServerListComponent,
    DetailsViewComponent,
    ChartComponent,
    VolumeListComponent,
    OverallViewComponent
  ],
  imports: [
    BrowserModule,
    MatTableModule,
    MatDialogModule,
    MatSortModule,
    MatButtonModule,
    MatDividerModule,
    MatFormFieldModule,
    MatToolbarModule,
    NoopAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    NgxChartsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
