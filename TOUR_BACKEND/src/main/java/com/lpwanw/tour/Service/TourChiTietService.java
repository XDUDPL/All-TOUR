package com.lpwanw.tour.Service;

import com.lpwanw.tour.Entity.TourChitietEntity;
import com.lpwanw.tour.Entity.TourDiadiemEntity;
import com.lpwanw.tour.Reponsitoty.TourChitietRepository;
import com.lpwanw.tour.Reponsitoty.TourDiadiemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class TourChiTietService {
    @Autowired
    private TourChitietRepository tourChitietRepository;
    public List<TourChitietEntity> GetAll(){
        return tourChitietRepository.findAll();
    }

    public Optional<TourChitietEntity> GetById (Integer id){
        return tourChitietRepository.findById(id);
    }

    public TourChitietEntity Insert (TourChitietEntity tourChitietEntity){
        List<TourChitietEntity> list = tourChitietRepository.findByTourID(tourChitietEntity.getTour().getId());
        tourChitietEntity.setCtThutu(list.size()+1);
        return tourChitietRepository.save(tourChitietEntity);
    }

    public TourChitietEntity Update (TourChitietEntity tourChitietEntity){
        TourChitietEntity item = tourChitietRepository.getById(tourChitietEntity.getId());
        List<TourChitietEntity> list = tourChitietRepository.findByTourID(tourChitietEntity.getTour().getId());
        TourChitietEntity swap = list.stream().filter(itemChild-> Objects.equals(itemChild.getCtThutu(), tourChitietEntity.getCtThutu())).findFirst().get();
        swap.setCtThutu(item.getCtThutu());
        tourChitietRepository.save(swap);
        return tourChitietRepository.save(tourChitietEntity);
    }

    public void Delete(TourChitietEntity e){
        List<TourChitietEntity> list = tourChitietRepository.findByTourID(e.getTour().getId());
        list.stream().filter(item-> item.getCtThutu()>e.getCtThutu()).forEach(item->{
            item.setCtThutu(item.getCtThutu()-1);
            tourChitietRepository.save(item);
        });
        tourChitietRepository.delete(e);
    }

    public  List<TourChitietEntity> getByTourId(Integer id){
        List<TourChitietEntity> list =tourChitietRepository.findByTourID(id);
        list.sort(Comparator.comparing(TourChitietEntity::getCtThutu));
        return  list;
    }
}
