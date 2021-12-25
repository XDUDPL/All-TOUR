package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.KhachHangEntity;
import com.tourdulich.tourdulich.Entity.NhanVienEntity;

import java.util.List;

public class NhanVienService {
    private static final String url = "http://localhost:8080/api/nhanvien";
    private static BaseRequest<NhanVienEntity> instance;
    public static BaseRequest<NhanVienEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,NhanVienEntity.class,NhanVienEntity[].class);
        }
        return instance;
    }

    public static List<NhanVienEntity> getData() {
        return getInstance().get();
    }
    public static NhanVienEntity add(NhanVienEntity nhanVienEntity){
        return getInstance().post(nhanVienEntity);
    }
    public static NhanVienEntity update(NhanVienEntity nhanVienEntity,Integer id){
        return getInstance().put(nhanVienEntity,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
}
