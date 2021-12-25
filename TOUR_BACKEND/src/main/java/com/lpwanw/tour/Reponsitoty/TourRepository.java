package com.lpwanw.tour.Reponsitoty;

import com.lpwanw.tour.Entity.TourEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourRepository extends JpaRepository<TourEntity, Integer> {
}