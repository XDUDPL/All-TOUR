package com.lpwanw.tour.Reponsitoty;

import com.lpwanw.tour.Entity.TourNhanvienEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourNhanvienRepository extends JpaRepository<TourNhanvienEntity, Integer> {
}