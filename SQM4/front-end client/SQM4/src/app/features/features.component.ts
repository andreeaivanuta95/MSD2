import { Component, OnInit } from '@angular/core';
import { DataService } from '../data.service';

@Component({
  selector: 'app-features',
  templateUrl: './features.component.html',
  styleUrls: ['./features.component.css']
})
export class FeaturesComponent implements OnInit {

  constructor(private dataService: DataService) { }
  featureById = {}
  features = [];

  ngOnInit() {
    this.dataService.setUrl('feature');
    this.dataService.getAll().subscribe(r => this.features = r);
  }

  public onGetById(id: any) {
    this.dataService.setUrl('feature');
    this.dataService.getById(id).subscribe(r => this.featureById = r);
  }

}
