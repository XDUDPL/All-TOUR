import { NgModule,Component, OnInit,enableProdMode } from '@angular/core';
import {TourService} from "../../services/tour.service";
import CustomStore from "devextreme/data/custom_store";
import {TourLoaiService} from "../../services/tour-loai.service";
import {Tour_Loai} from "../../shared/model/Tour_Loai";
import {Tour} from "../../shared/model/Tour";
import DevExpress from "devextreme";
import data = DevExpress.data;
import {TourChiTietService} from "../../services/tour-chi-tiet.service";

@Component({
  selector: 'app-tour',
  templateUrl: './tour.component.html',
  styleUrls: ['./tour.component.scss']
})
export class TourComponent implements OnInit {
  dataSource: any = {
    data: [],
    totalCount: 0
  };

  LoaiTour : Tour_Loai[] = [];

  tour :Tour[] = [];
  constructor(private tourService: TourService , private tourLoaiService: TourLoaiService , private tourChiTietService : TourChiTietService ) {
    this.tourLoaiService.getTourLoais().toPromise().then((data)=>{
      this.LoaiTour = data;
    })
    this.tourService.getAll().toPromise().then((data)=>{
      // data.forEach(e=>{
      //   this.tour.push(e)
      // })
      this.tour=data;
    })
  }
  getData() : void {
    this.dataSource = new CustomStore({
      key :["id"],
      load: ()=>{
        return this.tourService.getAll().toPromise().then((data)=>{
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
        this.tourService.get(key.id).subscribe(value =>{
          if(values.tour!=undefined) {
            this.tourService.get(values.tour.id).toPromise().then(data => {
              values.tour = data
              this.tourService.update({...value, ...values}).subscribe();
            })
          }else{
            this.tourService.update({...value, ...values}).subscribe();
          }
        })
      }, onInserted: (key, values) => {
        console.log(key)
        this.tourService.create({...key}).subscribe();
      }, remove: (values)=>{
        return values;
      }, onRemoved: (values)=>{
        console.log(values)
        // this.tourService.delete(values).subscribe();

      }
    });
  }

  ngOnInit(): void {
    this.getData();
  }

}
