import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OverallViewComponent } from './overall-view/overall-view.component';

const routes: Routes = [
  {path: '', component: OverallViewComponent},
  {path: 'servers', loadChildren: () => import('./server-list/server-list.module').then(m => m.ServerListModule)},
  { path: 'details-view/:name', loadChildren: () => import('./details-view/details-view.module').then(m => m.DetailsViewModule)}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
