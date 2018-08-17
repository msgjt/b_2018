import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DummyContentComponent} from './dummy-content/dummy-content.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';
import {AppContentRoutingModule} from './app-content-routing.module';
import {DummyService} from './dummy-content/shared/dummy.service';
import {AdminComponent} from './admin/admin.component';
import {AddUserComponent} from './admin/add-user/add-user.component';
import {AdminService} from './admin/shared/admin.service';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    AppContentRoutingModule

  ],
  declarations: [
    DummyContentComponent,
    AddUserComponent,
    AdminComponent
  ],
  providers: [DummyService, AdminService],
  exports: [
    AppContentRoutingModule,
  ]
})
export class AppContentModule {
}
