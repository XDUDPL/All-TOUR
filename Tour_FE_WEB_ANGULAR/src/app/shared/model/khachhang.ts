export interface Khachhang{
  id?: number;
  khTen: string;
  khSdt: string;
  khNgaysinh: Date;
  khEmail: string;
  khCmnd: string;
}
export interface PageResultKhachHang{
  totalCount: number;
  items: Khachhang[]
}
