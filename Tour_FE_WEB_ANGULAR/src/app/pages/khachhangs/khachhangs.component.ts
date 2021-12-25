import { Component, OnInit } from '@angular/core';
import {KhachhangService} from "../../services/khachhang.service";
import CustomStore from 'devextreme/data/custom_store';
@Component({
  selector: 'app-khachhangs',
  templateUrl: './khachhangs.component.html',
  styleUrls: ['./khachhangs.component.scss']
})
export class KhachhangsComponent implements OnInit {
  dataSource: any = {
    data: [],
    totalCount: 0
  };
  constructor(private khachHangService: KhachhangService) { }
  getKhachHang(): void {
    this.dataSource = new CustomStore({
      load: ()=>{
        return this.khachHangService.getAll().toPromise().then((data)=>{
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
        this.khachHangService.update({...key, ...values}).subscribe();
      }, onInserted: (key, values) => {
        this.khachHangService.create({...values}).subscribe();
      }, remove: (values)=>{
          return values;
      }, onRemoved: (values)=>{
        console.log(values)
          this.khachHangService.delete(values).subscribe();
      }
    });
  }
  ngOnInit(): void {
    this.getKhachHang();
  }

}
