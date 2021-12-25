package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.LoaiChiPhiEntity;

import java.util.List;

public class LoaiChiPhiService {
    private static final String url = "http://localhost:8080/api/loaichiphi";
    private static BaseRequest<LoaiChiPhiEntity> instance;
    public static BaseRequest<LoaiChiPhiEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,LoaiChiPhiEntity.class,LoaiChiPhiEntity[].class);
        }
        return instance;
    }

    public static List<LoaiChiPhiEntity> getData() {
        return getInstance().get();
    }
    public static LoaiChiPhiEntity getById(Integer id) {
        return getInstance().get(id);
    }
    public static LoaiChiPhiEntity add(LoaiChiPhiEntity loaiChiPhiEntity){
        return getInstance().post(loaiChiPhiEntity);
    }
    public static LoaiChiPhiEntity update(LoaiChiPhiEntity loaiChiPhiEntity,Integer id){
        return getInstance().put(loaiChiPhiEntity,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
}
