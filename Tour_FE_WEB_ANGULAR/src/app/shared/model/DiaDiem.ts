export interface DiaDiem {
  id? : number;
  ddThanhpho : string;
  ddTen : string;
  ddMota : string;
}
export interface PageResultDiaDiem{
  totalCount: number;
  items: DiaDiem[]
}
