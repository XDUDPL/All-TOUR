import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {DiaDiem} from "../shared/model/DiaDiem";


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}

@Injectable({
  providedIn: 'root'
})
export class DiaDiemService {
  private apiUrl: string ='http://localhost:8080/api/tourdiadiem';
  constructor(private http: HttpClient) {
    
    }
  getAll(): Observable<DiaDiem[]>{
    return this.http.get<DiaDiem[]>(this.apiUrl);
  }
  delete(diadiem: DiaDiem): Observable<DiaDiem>{
    const url = `${this.apiUrl}/${diadiem.id}`;
    return this.http.delete<DiaDiem>(url);
  }
  update(diadiem: DiaDiem): Observable<DiaDiem>{
    const url = `${this.apiUrl}/${diadiem.id}`;
    return this.http.put<DiaDiem>(url, diadiem, httpOptions);
  }
  create(diadiem: DiaDiem): Observable<DiaDiem>{
    return this.http.post<DiaDiem>(this.apiUrl, diadiem, httpOptions);
  }
}
