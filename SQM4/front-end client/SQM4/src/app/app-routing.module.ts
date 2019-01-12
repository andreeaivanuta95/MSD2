import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { BugsComponent } from './bugs/bugs.component';
import { FeaturesComponent } from './features/features.component';
import { ProjectsComponent } from './projects/projects.component';

const routes: Routes = [
  {
    path: 'users',
    component: HomeComponent,
    pathMatch: 'full'
  },
  {
    path: 'bugs',
    component: BugsComponent,
    pathMatch: 'full'
  },
  {
    path: 'features',
    component: FeaturesComponent,
    pathMatch: 'full'
  },
  {
    path: 'projects',
    component: ProjectsComponent,
    pathMatch: 'full'
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
