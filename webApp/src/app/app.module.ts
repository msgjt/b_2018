import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {AppRoutingModule} from './app-routing.module';
import {ErrorComponent} from './error/error.component';
import {HTTP_INTERCEPTORS, HttpClient, HttpClientModule} from '@angular/common/http';
import {UserHttpInterceptor} from './shared/user-http-interceptor';
import { LoginComponent } from './login/login.component';
import { ContentComponent } from './content/content.component';
import { DummyContentComponent } from './content/dummy-content/dummy-content.component';
import {AddUserComponent} from './content/add-user/add-user.component';
import {FormsModule} from '@angular/forms';
import {LoginService} from './login/shared/login.service';
import {ContentService} from './content/shared/content.service';
import {TranslateLoader, TranslateModule} from '@ngx-translate/core';
import {TranslateHttpLoader} from '@ngx-translate/http-loader';
import { EditUserComponent } from './content/edit-user/edit-user.component';
import {ShowUsersComponent} from './content/show-users/show-users.component';
import { WelcomeComponent } from './content/welcome/welcome.component';
import {AngularMultiSelectModule} from 'angular2-multiselect-dropdown';
import { AddBugComponent } from './add-bug/add-bug.component';
import {Ng2FileSizeModule} from 'ng2-file-size';
import {Data} from './shared/data';
import { EditRoleComponent } from './content/edit-role/edit-role.component';
import { RecaptchaModule } from 'angular-google-recaptcha';
import { ShowBugsComponent } from './content/show-bugs/show-bugs.component';
import { DataFilterPipe } from './content/show-bugs/data-filter.pipe';
import {EditBugComponent} from './edit-bug/edit-bug.component';

// TODO should be taken from somekind of config?
const SITE_KEY = '6LeLWmsUAAAAAPRM65RcHUjnHwdCmIJUTceUvP-k';

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
    WelcomeComponent,
    ShowBugsComponent,
    DataFilterPipe,
    AddBugComponent,
    EditRoleComponent,
    EditBugComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    Ng2FileSizeModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    }),
    AngularMultiSelectModule,
    RecaptchaModule.forRoot({
      siteKey: SITE_KEY,
    })
  ],
  providers: [
    Data,
    LoginService,
    ContentService,
    {provide: HTTP_INTERCEPTORS, useClass: UserHttpInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
