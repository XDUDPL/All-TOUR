package com.tourdulich.tourdulich.Service;

import com.tourdulich.tourdulich.Common.BaseRequest;
import com.tourdulich.tourdulich.Entity.NguoiDiEntity;
import com.tourdulich.tourdulich.Entity.TourChiPhiEntity;

import java.util.List;

public class NguoiDiService {
    private static final String url = "http://localhost:8080/api/nguoidi";
    private static BaseRequest<NguoiDiEntity> instance;
    public static BaseRequest<NguoiDiEntity> getInstance(){
        if(instance==null){
            instance = new BaseRequest<>(url,NguoiDiEntity.class,NguoiDiEntity[].class);
        }
        return instance;
    }
    public static List<NguoiDiEntity> getData() {
        return getInstance().get();
    }
    public static NguoiDiEntity add(NguoiDiEntity kh){
        return getInstance().post(kh);
    }
    public static NguoiDiEntity update(NguoiDiEntity kh,Integer id){
        return getInstance().put(kh,id);
    }
    public static boolean delete(Integer id){
        return getInstance().delete(id);
    }
    public static NguoiDiEntity getByDoanId(Integer id) {
        List<NguoiDiEntity> list = getInstance().getByPropID(id,"doan");
        if (list.isEmpty()){
            NguoiDiEntity entity = new NguoiDiEntity();
            entity.setDoan(TourDoanSerivce.getInstance().get(id));
            entity.setNguoidiDsnhanvien("[]");
            entity.setNguoidiDskhach("[]");
            entity = getInstance().post(entity);
            return entity;
        }
        return list.get(0);
    }
}
