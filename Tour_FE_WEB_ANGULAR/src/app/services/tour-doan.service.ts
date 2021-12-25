import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tour_Doan} from "../shared/model/Tour_Doan";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class TourDoanService {

  private apiUrl: string ='http://localhost:8080/api/doans';
  constructor(private http: HttpClient) { }
  getAll(): Observable<Tour_Doan[]>{
    return this.http.get<Tour_Doan[]>(this.apiUrl);
  }
  get(id: number): Observable<Tour_Doan>{
    const url = `${this.apiUrl}/${id}`;
    return this.http.get<Tour_Doan>(url);
  }
  delete(tour: Tour_Doan): Observable<Tour_Doan>{
    const url = `${this.apiUrl}/${tour.id}`;
    return this.http.delete<Tour_Doan>(url);
  }
  update(tour: Tour_Doan): Observable<Tour_Doan>{
    const url = `${this.apiUrl}/${tour.id}`;
    return this.http.put<Tour_Doan>(url, tour, httpOptions);
  }
  create(tour: Tour_Doan): Observable<Tour_Doan>{
    return this.http.post<Tour_Doan>(this.apiUrl, tour, httpOptions);
  }
}
