import { Component, OnInit } from '@angular/core';
import {NhanVienService} from "../../services/nhanvien.service";
import CustomStore from 'devextreme/data/custom_store';
@Component({
  selector: 'app-nhanviens',
  templateUrl: './nhanviens.component.html',
  styleUrls: ['./nhanviens.component.scss']
})
export class NhanViensComponent implements OnInit {
  dataSource: any = {
    data: [],
    totalCount: 0
  };
  constructor(private nhanVienService: NhanVienService) { }
  getNhanVien(): void {
    this.dataSource = new CustomStore({
      load: ()=>{
        return this.nhanVienService.getAll().toPromise().then((data)=>{
          return {
            data: data,
            totalCount: data.length
          }
        });
      }, insert: (values) => {
        delete values.__KEY__;
        return values;
      }, update: (values) => {
        return values;
      }, onUpdated: (key, values) => {
        this.nhanVienService.update({...key, ...values}).subscribe();
      }, onInserted: (key, values) => {
        this.nhanVienService.create({...values}).subscribe();
      }, remove: (values)=>{
          return values;
      }, onRemoved: (values)=>{
          this.nhanVienService.delete(values).subscribe();
      }
    });
  }
  ngOnInit(): void {
    this.getNhanVien();
  }

}
