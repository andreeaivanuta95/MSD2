import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  title = 'SQM4';
  content = [];
  userById: any;

  constructor(private dataService: DataService) { }

  ngOnInit() {
    this.getAll();
  }

  public getAll() {
    this.dataService.setUrl("user");
    this.dataService.getAll().subscribe(r => {
      this.content = r;
      console.log(this.content);
    });
  }

  public onGetById(idToLookup) {
    this.dataService.setUrl("user");
    this.dataService.getById(idToLookup).subscribe(
      r => {
        this.userById = r;
        console.log(r);
      });
  }

  public onDelete(id) {
    this.dataService.setUrl("user/delete");
    this.dataService.delete(id).subscribe();
  }

}
