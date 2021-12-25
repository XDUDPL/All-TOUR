package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.KhachhangEntity;
import com.lpwanw.tour.Reponsitoty.KhachhangRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class KhachHangService {

    @Autowired
    private KhachhangRepository khachhangRepository;

    public List<KhachhangEntity> findAll(){
        return khachhangRepository.findAll();
    }

    public Optional<KhachhangEntity> findById(int id){
        return khachhangRepository.findById(id);
    }

    public KhachhangEntity save(KhachhangEntity khachhangEntity){
        return khachhangRepository.save(khachhangEntity);
    }

    public KhachhangEntity update(KhachhangEntity khachhangEntity){
        return khachhangRepository.save(khachhangEntity);
    }

    public void delete(KhachhangEntity khachhangEntity){
        khachhangRepository.delete(khachhangEntity);
    }
}
