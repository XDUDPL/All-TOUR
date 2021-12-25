
import { NgModule,Component, ViewChild, enableProdMode, } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { AppComponent } from './app.component';
import { SideNavOuterToolbarModule, SideNavInnerToolbarModule, SingleCardModule } from './layouts';
import { FooterModule, ResetPasswordFormModule, CreateAccountFormModule, ChangePasswordFormModule, LoginFormModule } from './shared/components';
import {DxTabsModule, DxTemplateModule} from 'devextreme-angular';
import { AuthService, ScreenService, AppInfoService } from './shared/services';
import { UnauthenticatedContentModule } from './unauthenticated-content';
import { AppRoutingModule } from './app-routing.module';
import { TourLoaiComponent } from './pages/tour-loai/tour-loai.component';
import {TourLoaiService} from "./services/tour-loai.service";
import {KhachhangService} from "./services/khachhang.service";
import { KhachhangsComponent } from './pages/khachhangs/khachhangs.component';
import { TourComponent } from './pages/tour/tour.component';
import {NhanVienService} from "./services/nhanvien.service";
import {NhanViensComponent } from './pages/nhanviens/nhanviens.component';
import {TourService} from "./services/tour.service";
import { DetailGridComponent } from './pages/tour/detail-grid/detail-grid.component';
import {TourChiTietService} from "./services/tour-chi-tiet.service";
import { LoaiChiPhiComponent } from './pages/loai-chi-phi/loai-chi-phi.component';
import { ChiphiComponent } from './pages/chiphi/chiphi.component';
import {
  DxDropDownBoxModule,
  DxTreeViewModule,
  DxDataGridModule,
} from 'devextreme-angular';
import {DiaDiemService} from "./services/dia-diem.service";
import { DiaDiemComponent } from './pages/dia-diem/dia-diem.component';
import { DoanComponent } from './pages/doan/doan.component';
import {TourDoanService} from "./services/tour-doan.service";
import { DoanDetailComponent } from './pages/doan/doan-detail/doan-detail.component';
@NgModule({
  declarations: [
    AppComponent,
    TourLoaiComponent,
    KhachhangsComponent,
    TourComponent,
    DetailGridComponent,
    LoaiChiPhiComponent,
    NhanViensComponent,
    ChiphiComponent,
    DiaDiemComponent,
    DoanComponent,
    DoanDetailComponent,
  ],
  imports: [
    DxDropDownBoxModule,
    DxTreeViewModule,
    BrowserModule,
    SideNavOuterToolbarModule,
    SideNavInnerToolbarModule,
    SingleCardModule,
    FooterModule,
    ResetPasswordFormModule,
    CreateAccountFormModule,
    ChangePasswordFormModule,
    LoginFormModule,
    UnauthenticatedContentModule,
    AppRoutingModule,
    DxDataGridModule,
    HttpClientModule,
    DxTemplateModule,
    DxTabsModule,
  ],
  providers: [AuthService,
    ScreenService,
    AppInfoService,
    TourLoaiService,
    KhachhangService,
    NhanVienService,
    TourService,
    TourChiTietService,
    DiaDiemService,
    TourDoanService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
