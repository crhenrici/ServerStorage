import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { MatButtonModule, MatCardModule, MatDividerModule, MatIconModule, MatInputModule } from "@angular/material";
import { NgxChartsModule } from "@swimlane/ngx-charts";
import { ChartComponent } from "./chart/chart.component";
import { DetailsViewRoutingModule } from "./details-view-routing.module";
import { DetailsViewComponent } from "./details-view.component";

@NgModule({
declarations: [
    DetailsViewComponent,
    ChartComponent
],
imports: [
    MatDividerModule,
    MatCardModule,
    FormsModule,
    CommonModule,
    MatIconModule,
    MatInputModule,
    NgxChartsModule,
    MatButtonModule,
    DetailsViewRoutingModule
],
providers: [],
bootstrap: [DetailsViewComponent]
})
export class DetailsViewModule { }