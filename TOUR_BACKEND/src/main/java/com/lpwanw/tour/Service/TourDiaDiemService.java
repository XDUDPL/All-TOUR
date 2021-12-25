package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.TourDiadiemEntity;
import com.lpwanw.tour.Reponsitoty.TourDiadiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourDiaDiemService {
    @Autowired
    private TourDiadiemRepository tourDiadiemRepository;
    public List<TourDiadiemEntity> GetAll(){
        return tourDiadiemRepository.findAll();
    }

    public Optional<TourDiadiemEntity> GetById (Integer id){
        return tourDiadiemRepository.findById(id);
    }

    public TourDiadiemEntity Insert (TourDiadiemEntity tourDiadiemEntity){
        return tourDiadiemRepository.save(tourDiadiemEntity);
    }

    public TourDiadiemEntity Update (TourDiadiemEntity tourDiadiemEntity){
        return tourDiadiemRepository.save(tourDiadiemEntity);
    }

    public void Delete(Integer id){
        tourDiadiemRepository.deleteById(id);
    }
}
