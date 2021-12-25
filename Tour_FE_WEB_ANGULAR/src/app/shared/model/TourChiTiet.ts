import {Tour} from "./Tour";
import {DiaDiem} from "./DiaDiem";

export  interface TourChiTiet{
  id? : number;
  tour : Tour;
  dd : DiaDiem;
  ctThutu : number;
}
