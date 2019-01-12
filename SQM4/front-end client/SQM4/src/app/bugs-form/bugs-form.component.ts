import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';
import { Bug } from '../models/bug';

@Component({
  selector: 'app-bugs-form',
  templateUrl: './bugs-form.component.html',
  styleUrls: ['./bugs-form.component.css']
})
export class BugsFormComponent implements OnInit {

  constructor(private dataService: DataService) { }
  priorities = ['P1', 'P2', 'P3'];
  statuses = ['TO DO', 'IN PROGRESS', 'RESOLVED', 'IN TESTING', 'CLOSED', 'REOPENED'];
  model = new Bug();

  ngOnInit() {
  }
  onSubmit() {
    this.dataService.setUrl("bugs/add/");
    this.dataService.add(this.model);
  }
}
