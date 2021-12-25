package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.TourChiphiEntity;
import com.lpwanw.tour.Entity.TourNhanvienEntity;
import com.lpwanw.tour.Reponsitoty.TourChiphiRepository;
import com.lpwanw.tour.Reponsitoty.TourNhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourChiPhiService {

    @Autowired
    private TourChiphiRepository tourChiphiRepository;

    public List<TourChiphiEntity> findAll(){
        return tourChiphiRepository.findAll();
    }

//    public Optional<TourChiphiEntity> findById(int id){
//        return tourChiphiRepository.findB;
//    }

    public TourChiphiEntity save(TourChiphiEntity tourChiphiEntity){
        return tourChiphiRepository.save(tourChiphiEntity);
    }

    public TourChiphiEntity update(TourChiphiEntity tourChiphiEntity){
        return tourChiphiRepository.save(tourChiphiEntity);
    }

    public void delete(TourChiphiEntity tourChiphiEntity){
        tourChiphiRepository.delete(tourChiphiEntity);
    }
}
