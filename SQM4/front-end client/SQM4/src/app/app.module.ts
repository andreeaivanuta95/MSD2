import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from "@angular/common/http";
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormComponent } from './form/form.component';
import { HomeComponent } from './home/home.component';
import { BugsComponent } from './bugs/bugs.component';
import { FeaturesComponent } from './features/features.component';
import { ProjectsComponent } from './projects/projects.component';
import { BugsFormComponent } from './bugs-form/bugs-form.component';

@NgModule({
  declarations: [
    AppComponent,
    FormComponent,
    HomeComponent,
    BugsComponent,
    FeaturesComponent,
    ProjectsComponent,
    BugsFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
