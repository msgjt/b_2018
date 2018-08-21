import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {ErrorComponent} from './error/error.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {UserHttpInterceptor} from './shared/user-http-interceptor';
import { LoginComponent } from './login/login.component';
import { ContentComponent } from './content/content.component';
import { DummyContentComponent } from './content/dummy-content/dummy-content.component';
import {AddUserComponent} from './content/add-user/add-user.component';
import {FormsModule} from '@angular/forms';
import {LoginService} from './login/shared/login.service';
import {ContentService} from './content/shared/content.service';
import { EditUserComponent } from './content/edit-user/edit-user.component';
import {ShowUsersComponent} from './content/show-users/show-users.component';
import {AngularFontAwesomeModule} from 'angular-font-awesome';
import { WelcomeComponent } from './content/welcome/welcome.component';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {AngularMultiSelectModule} from 'angular2-multiselect-dropdown';
@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent,
    LoginComponent,
    ContentComponent,
    AddUserComponent,
    DummyContentComponent,
    EditUserComponent,
    ShowUsersComponent,
    WelcomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    AngularMultiSelectModule


  ],
  providers: [
    LoginService,
    ContentService,
    {provide: HTTP_INTERCEPTORS, useClass: UserHttpInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
