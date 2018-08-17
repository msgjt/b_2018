import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginComponent } from './login/login.component';
import {FormsModule} from '@angular/forms';
import {RouterModule, Routes} from '@angular/router';
import {HttpClientModule} from '@angular/common/http';
import {LoginService} from './shared/login.service';
import {AppLoginRoutingModule} from './app-login-routing.module';

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    HttpClientModule,
    AppLoginRoutingModule
  ],
  declarations: [LoginComponent],
  providers: [LoginService],
  exports: [
    LoginComponent,
    AppLoginRoutingModule
  ]
})
export class AppLoginModule { }
