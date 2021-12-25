import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Loaichiphi} from "../shared/model/loai-chi-phi";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class LoaiChiPhiService {
  private apiUrl: string ='http://localhost:8080/api/loaichiphi';
  constructor(private http: HttpClient) { }
  getAll(): Observable<Loaichiphi[]>{
    return this.http.get<Loaichiphi[]>(this.apiUrl);
  }
  delete(loaichiphi: Loaichiphi): Observable<Loaichiphi>{
    const url = `${this.apiUrl}/${loaichiphi.id}`;
    return this.http.delete<Loaichiphi>(url);
  }
  update(loaichiphi: Loaichiphi): Observable<Loaichiphi>{
    const url = `${this.apiUrl}/${loaichiphi.id}`;
    return this.http.put<Loaichiphi>(url, loaichiphi, httpOptions);
  }
  create(loaichiphi: Loaichiphi): Observable<Loaichiphi>{
    return this.http.post<Loaichiphi>(this.apiUrl, loaichiphi, httpOptions);
  }
}
