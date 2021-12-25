import { Injectable } from '@angular/core';
import {HttpClient,HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {NhanVien} from "../shared/model/nhanvien";
const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json'
  })
}
@Injectable({
  providedIn: 'root'
})
export class NhanVienService {
  private apiUrl: string ='http://localhost:8080/api/nhanvien';
  constructor(private http: HttpClient) { }
  getAll(): Observable<NhanVien[]>{
    return this.http.get<NhanVien[]>(this.apiUrl);
  }
  delete(nhanvien: NhanVien): Observable<NhanVien>{
    const url = `${this.apiUrl}/${nhanvien.id}`;
    return this.http.delete<NhanVien>(url);
  }
  update(nhanvien: NhanVien): Observable<NhanVien>{
    const url = `${this.apiUrl}/${nhanvien.id}`;
    return this.http.put<NhanVien>(url, nhanvien, httpOptions);
  }
  create(nhanvien: NhanVien): Observable<NhanVien>{
    return this.http.post<NhanVien>(this.apiUrl, nhanvien, httpOptions);
  }
}
