import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-bugs',
  templateUrl: './bugs.component.html',
  styleUrls: ['./bugs.component.css']
})
export class BugsComponent implements OnInit {

  constructor(private dataService: DataService) { }
  bugById = {}
  bugs = [];

  ngOnInit() {
    this.dataService.setUrl('bugs');
    this.dataService.getAll().subscribe(r => this.bugs = r);
  }

  public onGetById(id: any) {
    this.dataService.setUrl('bugs');
    this.dataService.getById(id).subscribe(r => this.bugById = r);
  }
}
