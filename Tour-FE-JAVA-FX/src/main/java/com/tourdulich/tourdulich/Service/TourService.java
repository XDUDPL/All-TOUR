package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.TourEntity;
import com.tourdulich.tourdulich.Entity.TourEntity;

import java.util.List;

public class TourService {
    private static final String url = "http://localhost:8080/api/tour";
    private static BaseRequest<TourEntity> instance;

    public static BaseRequest<TourEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,TourEntity.class,TourEntity[].class);
        }
        return instance;
    }

    public static List<TourEntity> getData() {
        return getInstance().get();
    }

    public static TourEntity add(TourEntity kh){
        return getInstance().post(kh);
    }

    public static TourEntity update(TourEntity kh,Integer id){
        return getInstance().put(kh,id);
    }

    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
}
