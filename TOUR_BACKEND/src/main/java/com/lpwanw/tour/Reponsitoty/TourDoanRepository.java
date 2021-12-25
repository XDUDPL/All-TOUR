package com.lpwanw.tour.Reponsitoty;

import com.lpwanw.tour.Entity.TourDoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TourDoanRepository extends JpaRepository<TourDoanEntity, Integer> {
}