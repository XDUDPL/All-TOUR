package com.lpwanw.tour.Service;


import com.lpwanw.tour.Entity.TourLoaichiphiEntity;
import com.lpwanw.tour.Entity.TourNhanvienEntity;
import com.lpwanw.tour.Reponsitoty.TourLoaichiphiRepository;
import com.lpwanw.tour.Reponsitoty.TourNhanvienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourLoaiChiPhiService {

    @Autowired
    private TourLoaichiphiRepository tourLoaichiphiRepository;

    public List<TourLoaichiphiEntity> findAll(){
        return tourLoaichiphiRepository.findAll();
    }

    public Optional<TourLoaichiphiEntity> findById(int id){
        return tourLoaichiphiRepository.findById(id);
    }

    public TourLoaichiphiEntity save(TourLoaichiphiEntity tourLoaichiphiEntity){
        return tourLoaichiphiRepository.save(tourLoaichiphiEntity);
    }

    public TourLoaichiphiEntity update(TourLoaichiphiEntity tourLoaichiphiEntity){
        return tourLoaichiphiRepository.save(tourLoaichiphiEntity);
    }

    public void delete(TourLoaichiphiEntity tourLoaichiphiEntity){
        tourLoaichiphiRepository.delete(tourLoaichiphiEntity);
    }
}
