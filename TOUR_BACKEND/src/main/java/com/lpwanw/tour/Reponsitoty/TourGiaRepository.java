package com.lpwanw.tour.Reponsitoty;

import com.lpwanw.tour.Entity.TourChitietEntity;
import com.lpwanw.tour.Entity.TourGiaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourGiaRepository extends JpaRepository<TourGiaEntity, Integer> {
    @Query(value = "select c from TourGiaEntity c where c.tourId = ?1")
    List<TourGiaEntity> findByTourID(Integer id);
}