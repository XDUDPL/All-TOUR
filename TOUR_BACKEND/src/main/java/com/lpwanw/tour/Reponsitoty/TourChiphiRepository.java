package com.lpwanw.tour.Reponsitoty;

import com.lpwanw.tour.Entity.TourChiphiEntity;
import com.lpwanw.tour.Entity.TourChitietEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourChiphiRepository extends JpaRepository<TourChiphiEntity, Integer> {
    @Query(value = "select c from TourChiphiEntity c where c.doan.id = ?1")
    List<TourChiphiEntity> findByDoanID(Integer id);
}