package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.TourChiPhiEntity;

import java.util.List;

public class TourChiPhiService {
    private static final String url = "http://localhost:8080/api/tourchiphi";
    private static BaseRequest<TourChiPhiEntity> instance;
    public static BaseRequest<TourChiPhiEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,TourChiPhiEntity.class,TourChiPhiEntity[].class);
        }
        return instance;
    }
    public static List<TourChiPhiEntity> getData() {
        return getInstance().get();
    }
    public static TourChiPhiEntity add(TourChiPhiEntity kh){
        return getInstance().post(kh);
    }
    public static TourChiPhiEntity update(TourChiPhiEntity kh,Integer id){
        return getInstance().put(kh,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
    public static TourChiPhiEntity getByDoanId(Integer id) {
        return getInstance().getByPropID(id,"doan").get(0);
    }
}
