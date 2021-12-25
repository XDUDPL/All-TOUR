package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.LoaiTourEntity;

import java.util.List;

public class LoaiTourService {
    private static final String url = "http://localhost:8080/api/tourloai";
    private static BaseRequest<LoaiTourEntity> instance;
    public static BaseRequest<LoaiTourEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,LoaiTourEntity.class,LoaiTourEntity[].class);
        }
        return instance;
    }

    public static List<LoaiTourEntity> getData() {
        return getInstance().get();
    }
    public static LoaiTourEntity add(LoaiTourEntity kh){
        return getInstance().post(kh);
    }
    public static LoaiTourEntity update(LoaiTourEntity kh,Integer id){
        return getInstance().put(kh,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
}
