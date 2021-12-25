package com.lpwanw.tour.Reponsitoty;

import com.lpwanw.tour.Entity.KhachhangEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface KhachhangRepository extends JpaRepository<KhachhangEntity, Integer>{

}