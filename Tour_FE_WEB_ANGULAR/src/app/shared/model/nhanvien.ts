export interface NhanVien{
    id?: number;
    nvTen: string;
    nvSdt: string;
    nvNgaysinh: Date;
    nvEmail: string;
    nvNhiemvu: string;
  }
  export interface PageResultNhanVien{
    totalCount: number;
    items: NhanVien[]
  }
  