import { CommonModule } from "@angular/common";
import { NgModule } from "@angular/core";
import {MatButtonModule, MatDialogModule, MatIconModule, MatInputModule, MatSortModule, MatTableModule} from '@angular/material';
import { DialogComponent } from "./dialog/dialog.component";
import { NotRoundPipe } from "../notRound.pipe";
import {ServerListComponent} from './server-list.component';
import {VolumeListComponent} from '../volume-list/volume-list.component';
import {FormsModule} from '@angular/forms';
import {ServerListRoutingModule} from './server-list-routing.module';


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
