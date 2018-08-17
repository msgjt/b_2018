import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ErrorComponent} from './error/error.component';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: '**', component: ErrorComponent}

];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes),
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {
}
