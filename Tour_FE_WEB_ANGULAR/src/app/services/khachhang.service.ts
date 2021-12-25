import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Khachhang} from "../shared/model/khachhang";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class KhachhangService {
  private apiUrl: string ='http://localhost:8080/api/khachhangs';
  constructor(private http: HttpClient) { }
  getAll(): Observable<Khachhang[]>{
    return this.http.get<Khachhang[]>(this.apiUrl);
  }
  delete(khachhang: Khachhang): Observable<Khachhang>{
    const url = `${this.apiUrl}/${khachhang.id}`;
    return this.http.delete<Khachhang>(url);
  }
  update(khachhang: Khachhang): Observable<Khachhang>{
    const url = `${this.apiUrl}/${khachhang.id}`;
    return this.http.put<Khachhang>(url, khachhang, httpOptions);
  }
  create(khachhang: Khachhang): Observable<Khachhang>{
    return this.http.post<Khachhang>(this.apiUrl, khachhang, httpOptions);
  }
}
