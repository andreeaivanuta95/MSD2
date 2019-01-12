import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class DataService {

  constructor(private http: HttpClient) { }

  url = '';
  userById = {};

  public setUrl(url: string) {
    this.url = "http://localhost:8080/SQM4/rest/";
    this.url += url;
  }

  public getAll(): Observable<any[]> {
    let result = this.http.get<any>(this.url);
    return result;
  }

  public add(item: any) {
    this.http.post(this.url, item).subscribe(
      r => console.log(r),
      e => console.log(e)
    );
  }

  public getById(id: any) {
    let result = this.http.get<any>(this.url + '/' + id);
    return result;
  }

  public delete(id: any) {
    let user = this.http.get<any>(this.url + '/' + id);
    let result = this.http.delete(this.url + '/' + id);
    return result;
  }
}
