import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import { MatButtonModule, MatSortModule, MatTableModule } from "@angular/material";
import { VolumeListComponent } from "../volume-list/volume-list.component";
import { ServerListRoutingModule } from "./server-list-routing.module";
import { ServerListComponent } from "./server-list.component";

@NgModule({
    declarations: [
        ServerListComponent,
        VolumeListComponent
    ],
    imports: [
        MatTableModule,
        MatSortModule,
        CommonModule,
        MatButtonModule,
        ServerListRoutingModule
    ],
    providers: [],
    bootstrap: []
})
export class ServerListModule {}