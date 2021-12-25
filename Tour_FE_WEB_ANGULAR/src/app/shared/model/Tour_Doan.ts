import {Tour} from "./Tour";
import {Tour_Gia} from "./Tour_Gia";

export interface Tour_Doan{
  id?: number;
  tour: Tour;
  doanName: string;
  doanNgaydi: Date;
  doanNgayve: Date;
  gia: Tour_Gia;
  doanChitietchuongtrinh: string;
}
