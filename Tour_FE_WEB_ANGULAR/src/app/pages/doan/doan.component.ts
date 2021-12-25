import { Component, OnInit } from '@angular/core';
import {TourDoanService} from "../../services/tour-doan.service";
import CustomStore from "devextreme/data/custom_store";
import {Tour} from "../../shared/model/Tour";
import {TourService} from "../../services/tour.service";

@Component({
  selector: 'app-doan',
  templateUrl: './doan.component.html',
  styleUrls: ['./doan.component.scss']
})
export class DoanComponent implements OnInit {
  dataSource: any = {
    data: [],
    totalCount: 0
  };
  tour: Tour[] = [];
  constructor(private tourDoanservice: TourDoanService,private tourService: TourService) {
    this.tourService.getAll().toPromise().then((data)=>{
      this.tour = data;
    })
  }
  tourx: any;
  getData() : void {
    this.dataSource = new CustomStore({
      key :["id"],
      load: ()=>{
        return this.tourDoanservice.getAll().toPromise().then((data)=>{
          return {
            data: data,
            totalCount: data.length
          }
        })
      }, insert: (values) => {
        delete values.__KEY__;
        return values;
      }, update: (values) => {
        return values;
      }, onUpdated: (key, values) => {
        this.tourDoanservice.get(key.id).subscribe(value =>{
          if(values.tour!=undefined) {
            this.tourService.get(values.tour.id).toPromise().then(data => {
              values.tour = data
              this.tourDoanservice.update({...value, ...values}).subscribe();
            })
          }else{
            this.tourDoanservice.update({...value, ...values}).subscribe();
          }
        })
      }, onInserted: (key, values) => {
        this.tourDoanservice.create({...values}).subscribe();
      }, remove: (values)=>{
        return values;
      }, onRemoved: (values)=>{
        this.tourDoanservice.delete(values).subscribe();
      }
    });
  }
  ngOnInit(): void {
    this.getData();
  }

}
