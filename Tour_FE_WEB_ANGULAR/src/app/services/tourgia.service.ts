import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Gia} from "../shared/model/Gia";

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})


export class TourgiaService {
  private apiUrl: string ='http://localhost:8080/api/tourgia';
  constructor(private http: HttpClient) {  }
  getAll(): Observable<Gia[]>{
    return this.http.get<Gia[]>(this.apiUrl);
  }
  delete(gia: Gia): Observable<Gia>{
    const url = `${this.apiUrl}/${gia.id}`;
    return this.http.delete<Gia>(url);
  }
  update(gia: Gia): Observable<Gia>{
    const url = `${this.apiUrl}/${gia.id}`;
    return this.http.put<Gia>(url, gia, httpOptions);
  }
  create(gia: Gia): Observable<Gia>{
    return this.http.post<Gia>(this.apiUrl, gia, httpOptions);
  }
}
