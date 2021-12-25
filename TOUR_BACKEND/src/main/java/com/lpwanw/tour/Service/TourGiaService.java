package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.TourGiaEntity;
import com.lpwanw.tour.Reponsitoty.TourGiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TourGiaService {
    @Autowired
    TourGiaRepository tourGiaRepository;
    public Optional<TourGiaEntity> getCurrentPriceByTourId(Integer id, LocalDate date){
        List<TourGiaEntity> list = tourGiaRepository.findByTourID(id);
        list.sort((o1, o2) -> 0);
        Optional<TourGiaEntity> entity = list.stream().filter(e->e.getGiaTungay().isAfter(date)).findFirst();
        return entity;
    }
}
