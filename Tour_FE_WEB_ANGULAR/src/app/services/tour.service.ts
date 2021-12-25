import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Khachhang} from "../shared/model/khachhang";
import {Tour} from "../shared/model/Tour";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class TourService {

  private apiUrl: string ='http://localhost:8080/api/tour';
  constructor(private http: HttpClient) { }
  getAll(): Observable<Tour[]>{
    return this.http.get<Tour[]>(this.apiUrl);
  }
  get(id: number): Observable<Tour[]>{
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Tour[]>(url);
  }
  delete(tour: Tour): Observable<Tour>{
    const url = `${this.apiUrl}/${tour.id}`;
    return this.http.delete<Tour>(url);
  }
  update(tour: Tour): Observable<Tour>{
    const url = `${this.apiUrl}/${tour.id}`;
    return this.http.put<Tour>(url, tour, httpOptions);
  }
  create(tour: Tour): Observable<Tour>{
    return this.http.post<Tour>(this.apiUrl, tour, httpOptions);
  }
}
