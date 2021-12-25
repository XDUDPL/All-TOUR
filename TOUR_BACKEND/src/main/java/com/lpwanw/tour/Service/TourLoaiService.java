package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.TourEntity;
import com.lpwanw.tour.Entity.TourLoaiEntity;
import com.lpwanw.tour.Reponsitoty.TourLoaiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourLoaiService {
    @Autowired
    private TourLoaiRepository tourLoaiRepository;

    public List<TourLoaiEntity> GetAll(){
        return tourLoaiRepository.findAll();
    }
    public Optional<TourLoaiEntity> GetById (Integer id){
        return tourLoaiRepository.findById(id);
    }

    public TourLoaiEntity Insert(TourLoaiEntity tourLoaiEntity){
       return tourLoaiRepository.save(tourLoaiEntity);
    }
    public TourLoaiEntity Update(TourLoaiEntity tourLoaiEntity){
        return tourLoaiRepository.save(tourLoaiEntity);
    }

    public void Delete(Integer id){
        tourLoaiRepository.deleteById(id);
    }


}
