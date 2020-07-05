import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ServerListComponent } from './server-list/server-list.component';
import { MatTableModule, MatDialogModule, MatSortModule, MatFormFieldModule, MatFormFieldControl, MatButtonModule } from '@angular/material';
import { NoopAnimationsModule } from '@angular/platform-browser/animations';
import { DetailsViewComponent } from './details-view/details-view.component';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    ServerListComponent,
    DetailsViewComponent
  ],
  imports: [
    BrowserModule,
    MatTableModule,
    MatDialogModule,
    MatSortModule,
    MatButtonModule,
    MatFormFieldModule,
    NoopAnimationsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
