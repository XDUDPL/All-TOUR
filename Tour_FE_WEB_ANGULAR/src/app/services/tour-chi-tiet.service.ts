import { Injectable } from '@angular/core';
import {Task} from "../shared/model/Task";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TourChiTiet} from "../shared/model/TourChiTiet";
import {Tour} from "../shared/model/Tour";
import {Khachhang} from "../shared/model/khachhang";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})

export class TourChiTietService {
  private apiUrl: string ='http://localhost:8080/api/tourchitiet';

  constructor(private http: HttpClient) { }
  getTourChiTiet(): Observable<TourChiTiet[]>{
    return this.http.get<TourChiTiet[]>(this.apiUrl);
  }
  deleteTourChiTiet(tourChiTiet: TourChiTiet): Observable<TourChiTiet>{
    const url = `${this.apiUrl}/${tourChiTiet.id}`;
    return this.http.delete<TourChiTiet>(url);
  }
  updateTourChiTiet(tourChiTiet: TourChiTiet): Observable<TourChiTiet>{
    const url = `${this.apiUrl}/${tourChiTiet.id}`;
    return this.http.put<TourChiTiet>(url, tourChiTiet, httpOptions);
  }

  deleteTourChiTietByTourID(id: number): Observable<TourChiTiet>{
    const url = `${this.apiUrl}/tourid/${id}`;
    return this.http.delete<TourChiTiet>(url);
  }
  create(tourChiTiet: TourChiTiet): Observable<TourChiTiet>{
    return this.http.post<TourChiTiet>(this.apiUrl, tourChiTiet, httpOptions);
  }

}
