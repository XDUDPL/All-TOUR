import {AfterViewInit, Component, Input, OnInit} from '@angular/core';
import {TourChiPhiService} from "../../../services/tour-chi-phi.service";
import {TourChiPhi} from "../../../shared/model/Tour_Chi_Phi";
import DataSource from "devextreme/data/data_source";
import ArrayStore from "devextreme/data/array_store";
import {ChiPhiDetail} from "../../../shared/model/ChiPhiDetail";
import {Loaichiphi} from "../../../shared/model/loai-chi-phi";
import {LoaiChiPhiService} from "../../../services/loai-chi-phi.service";

@Component({
  selector: 'app-doan-detail',
  templateUrl: './doan-detail.component.html',
  styleUrls: ['./doan-detail.component.scss']
})
export class DoanDetailComponent implements AfterViewInit {
  chiPhiDataSource: any = {
    data: [],
    chiphiTotal: 0,
    totalCount: 0
  };
  loaichiphi: Loaichiphi[] = [];
  tourChiPhi: TourChiPhi[] =[] ;
  chiphi: ChiPhiDetail[] = [];
  // @ts-ignore
  @Input() key : number;
  constructor(private service: TourChiPhiService, private loaiChiPhiService: LoaiChiPhiService) {
    this.service.getAll().toPromise().then((data) =>{
      data.forEach(e=>{
        this.tourChiPhi.push(e);
      })
    })
    this.loaiChiPhiService.getAll().toPromise().then((data)=>{
      this.loaichiphi = data;
    })
  }

  ngAfterViewInit(): void {
    this.chiPhiDataSource = new DataSource({
      load: ()=> {
        return this.service.getByDoanID(this.key).toPromise().then((data)=>{
          return {
            data: JSON.parse(data[0].chiphiChitiet),
            chiphiTotal: data[0].chiphiTotal
          }
        })
      }
    });
  }

}
