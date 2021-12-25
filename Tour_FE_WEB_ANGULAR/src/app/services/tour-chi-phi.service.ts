import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {TourChiPhi} from "../shared/model/Tour_Chi_Phi";
import {ChiPhiDetail} from "../shared/model/ChiPhiDetail";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class TourChiPhiService {

  private apiUrl: string ='http://localhost:8080/api/tourchiphi';
  constructor(private http: HttpClient) { }
  getAll(): Observable<TourChiPhi[]>{
    return this.http.get<TourChiPhi[]>(this.apiUrl);
  }
  getByDoanID(id: number): Observable<TourChiPhi[]>{
    const url = `${this.apiUrl}/doan/${id}`;
    return this.http.get<TourChiPhi[]>(url);
  }
  delete(tourChiPhi: TourChiPhi): Observable<TourChiPhi>{
    const url = `${this.apiUrl}/${tourChiPhi.id}`;
    return this.http.delete<TourChiPhi>(url);
  }
  update(tourChiPhi: TourChiPhi): Observable<TourChiPhi>{
    const url = `${this.apiUrl}/${tourChiPhi.id}`;
    return this.http.put<TourChiPhi>(url, tourChiPhi, httpOptions);
  }
  create(tourChiPhi: TourChiPhi): Observable<TourChiPhi>{
    return this.http.post<TourChiPhi>(this.apiUrl, tourChiPhi, httpOptions);
  }
}
