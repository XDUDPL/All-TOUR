package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.TourEntity;
import com.lpwanw.tour.Reponsitoty.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TourService {
    @Autowired
    private TourRepository tourRepository;

//    public List<TourDTO> GetAll (){
//        TourEntityToTourDTO tourEntityToTourDTO = new TourEntityToTourDTO();
//        List<TourEntity> listTours = new ArrayList<>();
//        listTours = tourRepository.findAll();
//        List<TourDTO>  tourDTOS = new ArrayList<>();
//        for(TourEntity tour:listTours){
//            TourDTO tour1 = new TourDTO();
//            tour1 = tourEntityToTourDTO.EntityToDto(tour);
//            tourDTOS.add( tour1 );
//        }
//        return tourDTOS;
//    }
    public List<TourEntity> GetAll(){
    return tourRepository.findAll();
}

    public Optional<TourEntity> GetById (Integer id){
        return tourRepository.findById(id);
    }

    public TourEntity Insert (TourEntity tourEntity){
        return tourRepository.save(tourEntity);
    }

    public TourEntity Update (TourEntity tourEntity){
        return tourRepository.save(tourEntity);
    }

    public void Delete(Integer id){
        tourRepository.deleteById(id);
    }

}
