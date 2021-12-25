package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.TourGiaEntity;

import java.util.Comparator;
import java.util.List;

public class TourGiaService {
    private static final String url = "http://localhost:8080/api/tourgia";
    private static BaseRequest<TourGiaEntity> instance;
    public static BaseRequest<TourGiaEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,TourGiaEntity.class,TourGiaEntity[].class);
        }
        return instance;
    }
    public static List<TourGiaEntity> getData() {
        return getInstance().get();
    }
    public static TourGiaEntity add(TourGiaEntity kh){
        return getInstance().post(kh);
    }
    public static TourGiaEntity update(TourGiaEntity kh,Integer id){
        return getInstance().put(kh,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
    public static List<TourGiaEntity> getByTourId(Integer id) {
        List<TourGiaEntity> list = getInstance().getByPropID(id,"tour");
        list.sort(new Comparator<TourGiaEntity>() {
            @Override
            public int compare(TourGiaEntity o1, TourGiaEntity o2) {
                return o1.getGiaTungay().compareTo(o2.getGiaTungay());
            }
        });
        return list;
    }
}
