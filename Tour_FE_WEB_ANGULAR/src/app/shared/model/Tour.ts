import {Tour_Loai} from "./Tour_Loai";

export interface Tour{
  id?: number;
  tourTen: string;
  tourMoTa: string;
  loai : Tour_Loai;
}
