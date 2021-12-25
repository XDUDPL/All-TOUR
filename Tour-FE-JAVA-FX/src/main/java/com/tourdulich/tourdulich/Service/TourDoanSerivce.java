package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.DoanEntity;
import com.tourdulich.tourdulich.Entity.TourChiTietEntity;

import java.util.List;

public class TourDoanSerivce {
    private static final String url = "http://localhost:8080/api/doans";
    private static BaseRequest<DoanEntity> instance;
    public static BaseRequest<DoanEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,DoanEntity.class,DoanEntity[].class);
        }
        return instance;
    }
    public static List<DoanEntity> getData() {
        return getInstance().get();
    }
    public static DoanEntity add(DoanEntity kh){
        return getInstance().post(kh);
    }
    public static DoanEntity update(DoanEntity kh,Integer id){
        return getInstance().put(kh,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
}
