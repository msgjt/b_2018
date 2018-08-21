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
@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent,
    LoginComponent,
    ContentComponent,
    AddUserComponent,
    DummyContentComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    TranslateModule.forRoot({
      loader: {
        provide: TranslateLoader,
        useFactory: HttpLoaderFactory,
        deps: [HttpClient]
      }
    })

  ],
  providers: [
    LoginService,
    ContentService,
    {provide: HTTP_INTERCEPTORS, useClass: UserHttpInterceptor, multi: true},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }

// required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http);
}
