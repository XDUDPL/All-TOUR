package com.tourdulich.tourdulich.Service;


import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.KhachHangEntity;

import java.util.List;

public class KhachHangService{
    private static final String url = "http://localhost:8080/api/khachhang";
    private static BaseRequest<KhachHangEntity> instance;
    public static BaseRequest<KhachHangEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,KhachHangEntity.class,KhachHangEntity[].class);
        }
        return instance;
    }

    public static List<KhachHangEntity> getData() {
        return getInstance().get();
    }
    public static KhachHangEntity add(KhachHangEntity kh){
        return getInstance().post(kh);
    }
    public static KhachHangEntity update(KhachHangEntity kh,Integer id){
        return getInstance().put(kh,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
}
