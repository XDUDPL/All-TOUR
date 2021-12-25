import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Tour_Loai} from "../shared/model/Tour_Loai";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class TourLoaiService {
  private apiUrl: string ='http://localhost:8080/api/tourloai';
  constructor(private http: HttpClient) { }
  getTourLoais(): Observable<Tour_Loai[]>{
    return this.http.get<Tour_Loai[]>(this.apiUrl);
  }
  deleteTourLoai(tourLoai: Tour_Loai): Observable<Tour_Loai>{
    const url = `${this.apiUrl}/${tourLoai.id}`;
    return this.http.delete<Tour_Loai>(url);
  }
  updateTourLoai(tourLoai: Tour_Loai): Observable<Tour_Loai>{
    const url = `${this.apiUrl}/${tourLoai.id}`;
    return this.http.put<Tour_Loai>(url, tourLoai, httpOptions);
  }
  addTourLoai(tourLoai: Tour_Loai): Observable<Tour_Loai>{
    return this.http.post<Tour_Loai>(this.apiUrl, tourLoai, httpOptions);
  }
}
