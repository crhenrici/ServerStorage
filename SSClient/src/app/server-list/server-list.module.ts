import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import {MatButtonModule, MatDialogModule, MatIconModule, MatInputModule, MatSortModule, MatTableModule} from '@angular/material';
import { VolumeListComponent } from "../volume-list/volume-list.component";
import { ServerListRoutingModule } from "./server-list-routing.module";
import { ServerListComponent } from "./server-list.component";
import {FormsModule} from '@angular/forms';
import { DialogComponent } from "./dialog/dialog.component";
import { NotRoundPipe } from "../notRound.pipe";

@NgModule({
    declarations: [
        ServerListComponent,
        VolumeListComponent,
        DialogComponent,
        NotRoundPipe
    ],
    imports: [
        MatTableModule,
        MatSortModule,
        MatIconModule,
        MatInputModule,
        MatDialogModule,
        FormsModule,
        CommonModule,
        MatButtonModule,
        ServerListRoutingModule
    ],
    providers: [],
    bootstrap: [],
    entryComponents: [DialogComponent]
})
export class ServerListModule {}
