import { Component, OnInit } from '@angular/core';
import {DiaDiemService} from "../../services/dia-diem.service";
import CustomStore from 'devextreme/data/custom_store';
@Component({
  selector: 'app-diadiem',
  templateUrl: './dia-diem.component.html',
  styleUrls: ['./dia-diem.component.scss']
})
export class DiaDiemComponent implements OnInit {

  dataSource: any = {
    data: [],
    totalCount: 0
  };
  constructor(private diaDiemService: DiaDiemService) {
    this.diaDiemService.getAll().toPromise().then((data)=>{
      // console.log("đia diem truoc return",data);
      data.forEach(e => {
        console.log(e)
      })
    })
   }
  ngOnInit(): void {
    this.getDiaDiem();
  }
  getDiaDiem(): void {
    this.dataSource = new CustomStore({
      load: ()=>{
  
        return this.diaDiemService.getAll().toPromise().then((data)=>{
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
        this.diaDiemService.update({...key, ...values}).subscribe();
      }, onInserted: (key, values) => {
        this.diaDiemService.create({...values}).subscribe();
      }, remove: (values)=>{
          return values;
      }, onRemoved: (values)=>{
          // this.diaDiemService.delete(values).subscribe();
          this.diaDiemService.delete(values).subscribe();
      }
    });
  }

}
