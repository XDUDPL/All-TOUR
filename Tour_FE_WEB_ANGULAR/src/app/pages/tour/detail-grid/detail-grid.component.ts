import {Component, Input, AfterViewInit, OnInit} from '@angular/core';
import DataSource from 'devextreme/data/data_source';
import ArrayStore from 'devextreme/data/array_store';
import {TourChiTietService} from "../../../services/tour-chi-tiet.service";
import {TourChiTiet} from "../../../shared/model/TourChiTiet";
import {Observable} from "rxjs";
import DevExpress from "devextreme";
import data = DevExpress.data;
import CustomStore from "devextreme/data/custom_store";
import {Tour} from "../../../shared/model/Tour";
import {Gia} from "../../../shared/model/Gia";
import {TourgiaService} from "../../../services/tourgia.service";

interface Tab {
  text : string;
}

@Component({
  selector: 'detail-grid1',
  templateUrl: './detail-grid.component.html',
  providers: [TourChiTietService]
})
export class DetailGridComponent implements AfterViewInit , OnInit {
  isDiaDiemShow : boolean = true;
  dataSource1: any = {
    data: [],
    totalCount: 0
  };
  dataSource2: any = {
    data: [],
    totalCount: 0
  };


  tab : Tab[] = [
    {text : "dia diem"},
    {text : "gia"}
  ];
  // @ts-ignore
  @Input() key : number;
  // @ts-ignore

  tourChiTietDataSource : DataSource;
  // @ts-ignore
  giaDataSource : DataSource;

  tourChiTiet: TourChiTiet[] =[] ;

  gia:Gia[] = [];

  constructor(private service: TourChiTietService , private giaService : TourgiaService) {
    this.service.getTourChiTiet().toPromise().then((data) =>{
      data.forEach(e=>{
        this.tourChiTiet.push(e);
      })
    })

    this.giaService.getAll().toPromise().then((data)=>{
      data.forEach(e=>{
        this.gia.push(e);
      })
    })

  }

  ngAfterViewInit() {

    this.tourChiTietDataSource = new DataSource({
      store: new ArrayStore({
        data: this.tourChiTiet,
        key: "id",
      }),
      filter: ["tour.id", "=", this.key]
    });

    this.giaDataSource = new DataSource({
      store: new ArrayStore({
        data: this.gia,
        key: "id",
      }),
      filter: ["tourId", "=", this.key]
    });
    console.log(this.gia);

  }
  completedValue(rowData : any) {
    return rowData.Status == "Completed";
  }

  selectTab($event: any) {
    this.isDiaDiemShow = !this.isDiaDiemShow;
  }
  getData1() : void {
    this.dataSource1 = new CustomStore({
      key :["id"],
      load: ()=>{
        return this.service.getTourChiTiet().toPromise().then((data)=>{
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

      }, onInserted: (key, values) => {
        console.log(key)
        this.service.create({...key}).subscribe();
      }, remove: (values)=>{
        return values;
      }, onRemoved: (values)=>{
        console.log(values)
        // this.tourService.delete(values).subscribe();
      }
    });
  }

  ngOnInit(): void {
    this.getData1();
  }
}
