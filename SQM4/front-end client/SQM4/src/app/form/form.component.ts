import { Component, OnInit } from '@angular/core';
import { User } from '../models/user';
import { DataService } from '../data.service';

@Component({
  selector: 'app-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  constructor(private dataService: DataService) { }

  model = new User('','','None','');
  roles = ['Developer', 'Tester', 'Project Manager']
  ngOnInit() {
  }

  onSubmit(){
    this.dataService.setUrl("user/add/");
    this.dataService.add(this.model);
  }

}
