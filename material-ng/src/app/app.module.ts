import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import{BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {MaterialModule} from './material';
import { MatIconModule } from '@angular/material';
import { RegisterComponent } from './component/user/register.componant';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginComponent } from './component/user/login.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { HttpClientModule } from '@angular/common/http';
import { UserService } from './services/user.service';
import { LoginService } from './services/login.service';
import { UserListComponent } from './component/user/user-list.componant';

@NgModule({
  declarations: [
    AppComponent,RegisterComponent,LoginComponent,UserListComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MaterialModule,
    MatIconModule,
    FlexLayoutModule,
    ReactiveFormsModule,
    HttpClientModule
    
    
  ],
  providers: [UserService,LoginService],
  bootstrap: [AppComponent]
})
export class AppModule { }
