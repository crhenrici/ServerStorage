import { Component, OnInit } from "@angular/core";
import { MatDialogRef } from "@angular/material";

@Component({
    selector: 'app-dialog',
    templateUrl: './dialog.component.html',
    styleUrls: ['./dialog.component.css']
  })
  export class DialogComponent implements OnInit {

    fileName: string;

    constructor(private dialogRef: MatDialogRef<DialogComponent>) {
      this.fileName = '';
    }
  
    ngOnInit() {}

    onClose() {
      this.dialogRef.close({ data: this.fileName });
    }
  }