package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.TourDoanEntity;
import com.lpwanw.tour.Reponsitoty.TourDoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourDoanService {
    @Autowired
    private TourDoanRepository tourDoanRepository;
    public List<TourDoanEntity> getAll(){
        return tourDoanRepository.findAll();
    }
    public Optional<TourDoanEntity> getById(Integer id){
        return tourDoanRepository.findById(id);
    }
    public TourDoanEntity insert(TourDoanEntity tourDoanEntity){
        return tourDoanRepository.save(tourDoanEntity);
    }
    public TourDoanEntity update(TourDoanEntity tourDoanEntity,Integer id){
        Optional<TourDoanEntity> entity = tourDoanRepository.findById(id);
        if(entity.isPresent()){
            tourDoanEntity.setId(id);
            return tourDoanRepository.save(tourDoanEntity);
        }else{
            return tourDoanEntity;
        }
    }
    public void delete(Integer id){
        Optional<TourDoanEntity> entity = tourDoanRepository.findById(id);
        if(entity.isPresent()){
            tourDoanRepository.deleteById(id);
        }
    }
}
