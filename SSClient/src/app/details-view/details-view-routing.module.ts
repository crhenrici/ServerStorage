import { NgModule } from "@angular/core";
import { RouterModule, Routes } from "@angular/router";
import { DetailsViewComponent } from "./details-view.component";

const routes: Routes = [
    {
        path: '', component: DetailsViewComponent
    }
]

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
  })
  export class DetailsViewRoutingModule { }