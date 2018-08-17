import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {DummyContentComponent} from './dummy-content/dummy-content.component';
import {AdminComponent} from './admin/admin.component';
import {AddUserComponent} from './admin/add-user/add-user.component';

const routes: Routes = [
  {path: 'content', component: DummyContentComponent},
  {path: 'admin', component: AdminComponent},
  {path: 'admin/addUser', component: AddUserComponent},
];

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forChild(routes),
  ],
  exports: [RouterModule]
})
export class AppContentRoutingModule {
}
