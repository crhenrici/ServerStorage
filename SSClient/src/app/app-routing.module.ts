import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DetailsViewComponent } from './details-view/details-view.component';
import { OverallViewComponent } from './overall-view/overall-view.component';
import { ServerListComponent } from './server-list/server-list.component';

const routes: Routes = [
  {path: '', component: OverallViewComponent},
  {path: 'servers', component: ServerListComponent},
  { path: 'details-view/:name', component: DetailsViewComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
