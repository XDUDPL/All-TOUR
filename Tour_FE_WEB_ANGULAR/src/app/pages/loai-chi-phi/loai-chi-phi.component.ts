import { Component, OnInit } from '@angular/core';
import {LoaiChiPhiService} from "../../services/loai-chi-phi.service";
import CustomStore from 'devextreme/data/custom_store';
@Component({
  selector: 'app-loai-chi-phi',
  templateUrl: './loai-chi-phi.component.html',
  styleUrls: ['./loai-chi-phi.component.scss']
})
export class LoaiChiPhiComponent implements OnInit {
  dataSource: any = {
    data: [],
    totalCount: 0
  };

  constructor(private loaichiphiService: LoaiChiPhiService) { }
  getLoaiChiPhi(): void {
    this.dataSource = new CustomStore({
      load: ()=>{
        return this.loaichiphiService.getAll().toPromise().then((data)=>{
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
        this.loaichiphiService.update({...key, ...values}).subscribe();
      }, onInserted: (key, values) => {
        this.loaichiphiService.create({...values}).subscribe();
      }, remove: (values)=>{
          return values;
      }, onRemoved: (values)=>{
          this.loaichiphiService.delete(values).subscribe();
      }
    });
  }
  ngOnInit(): void {
    this.getLoaiChiPhi();
  }


}
