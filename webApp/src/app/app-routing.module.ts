import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {ErrorComponent} from './error/error.component';
import {LoginComponent} from './login/login.component';
import {AddUserComponent} from './content/add-user/add-user.component';
import {DummyContentComponent} from './content/dummy-content/dummy-content.component';
import {EditUserComponent} from './content/edit-user/edit-user.component';
import {ShowUsersComponent} from './content/show-users/show-users.component';
import {WelcomeComponent} from './content/welcome/welcome.component';
import {ShowBugsComponent} from './content/show-bugs/show-bugs.component';

const routes: Routes = [
  {path: '', redirectTo: '/login', pathMatch: 'full'},
  {path: 'login', component: LoginComponent},
  {path: 'content/add', component: AddUserComponent},
  {path: 'content/dummy', component: DummyContentComponent},
  {path: 'showUsers', component: ShowUsersComponent},
  {path: 'editUser', component: EditUserComponent},
  {path: 'welcome', component: WelcomeComponent},
  {path: 'bugs', component: ShowBugsComponent},
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
