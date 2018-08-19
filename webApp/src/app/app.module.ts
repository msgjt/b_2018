import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {AppLoginModule} from './app-login/app-login.module';
import {AppContentModule} from './app-content/app-content.module';
import {ErrorComponent} from './error/error.component';
import {HTTP_INTERCEPTORS} from '@angular/common/http';
import {UserHttpInterceptor} from './shared/user-http-interceptor';
@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    AppLoginModule,
    AppContentModule,
    AppRoutingModule,


  ],
  providers: [
    {provide: HTTP_INTERCEPTORS, useClass: UserHttpInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
