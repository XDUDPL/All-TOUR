package com.lpwanw.tour.Reponsitoty;

import com.lpwanw.tour.Entity.TourChiphiEntity;
import com.lpwanw.tour.Entity.TourNguoidiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TourNguoidiRepository extends JpaRepository<TourNguoidiEntity, Integer> {
    @Query(value = "select c from TourNguoidiEntity c where c.doan.id = ?1")
    List<TourNguoidiEntity> findByDoanID(Integer id);
}