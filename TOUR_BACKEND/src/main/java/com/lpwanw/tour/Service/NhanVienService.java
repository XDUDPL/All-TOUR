package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.TourNhanvienEntity;
import com.lpwanw.tour.Reponsitoty.TourNhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhanVienService {

    @Autowired
    private TourNhanvienRepository tourNhanvienRepository;

    public List<TourNhanvienEntity> findAll(){
        return tourNhanvienRepository.findAll();
    }

    public Optional<TourNhanvienEntity> findById(int id){
        return tourNhanvienRepository.findById(id);
    }

    public TourNhanvienEntity save(TourNhanvienEntity NhanVienEntity){
        return tourNhanvienRepository.save(NhanVienEntity);
    }

    public TourNhanvienEntity update(TourNhanvienEntity NhanVienEntity){
        return tourNhanvienRepository.save(NhanVienEntity);
    }

    public void delete(TourNhanvienEntity NhanVienEntity){
        tourNhanvienRepository.delete(NhanVienEntity);
    }
}
