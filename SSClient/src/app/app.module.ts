import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServerListComponent } from './server-list/server-list.component';
import { MatTableModule, MatDialogModule, MatSortModule, MatFormFieldModule, MatButtonModule, MatDividerModule } from '@angular/material';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { DetailsViewComponent } from './details-view/details-view.component';
import { HttpClientModule } from '@angular/common/http';
import { ChartComponent } from './chart/chart.component';
import { NgxLineChartModule } from 'ngx-line-chart';

@NgModule({
  declarations: [
    AppComponent,
    ServerListComponent,
    DetailsViewComponent,
    ChartComponent
  ],
  imports: [
    BrowserModule,
    MatTableModule,
    MatDialogModule,
    MatSortModule,
    MatButtonModule,
    MatDividerModule,
    MatFormFieldModule,
    NoopAnimationsModule,
    HttpClientModule,
    AppRoutingModule,
    NgxLineChartModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule { }
