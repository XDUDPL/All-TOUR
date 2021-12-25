package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.DiaDiemEntity;

import java.util.List;

public class DiaDiemService {
    private static final String url = "http://localhost:8080/api/tourdiadiem";
    private static BaseRequest<DiaDiemEntity> instance;

    public static BaseRequest<DiaDiemEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,DiaDiemEntity.class,DiaDiemEntity[].class);
        }
        return instance;
    }

    public static List<DiaDiemEntity> getData() {
        return getInstance().get();
    }

    public static DiaDiemEntity add(DiaDiemEntity dd){
        return getInstance().post(dd);
    }

    public static DiaDiemEntity update(DiaDiemEntity dd,Integer id){
        return getInstance().put(dd,id);
    }

    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }

}
