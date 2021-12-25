package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.LoaiTourEntity;
import com.tourdulich.tourdulich.Entity.TourChiTietEntity;

import java.util.List;

public class TourChiTietService {
    private static final String url = "http://localhost:8080/api/tourchitiet";
    private static BaseRequest<TourChiTietEntity> instance;
    public static BaseRequest<TourChiTietEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,TourChiTietEntity.class,TourChiTietEntity[].class);
        }
        return instance;
    }
    public static List<TourChiTietEntity> getData() {
        return getInstance().get();
    }
    public static TourChiTietEntity add(TourChiTietEntity kh){
        return getInstance().post(kh);
    }
    public static TourChiTietEntity update(TourChiTietEntity kh,Integer id){
        return getInstance().put(kh,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
    public static List<TourChiTietEntity> getByTourId(Integer id) {
        return getInstance().getByPropID(id,"tour");
    }
}
