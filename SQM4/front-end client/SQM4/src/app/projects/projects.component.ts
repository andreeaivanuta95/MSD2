import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-projects',
  templateUrl: './projects.component.html',
  styleUrls: ['./projects.component.css']
})
export class ProjectsComponent implements OnInit {

  constructor(private dataService: DataService) { }
  projectById = {}
  projects = [];

  ngOnInit() {
    this.dataService.setUrl('projects');
    this.dataService.getAll().subscribe(r => this.projects = r);
  }

  public onGetById(id: any) {
    this.dataService.setUrl('projects');
    this.dataService.getById(id).subscribe(r => this.projectById = r);
  }

}
