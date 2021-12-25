package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.TourChiphiEntity;
import com.lpwanw.tour.Entity.TourChitietEntity;
import com.lpwanw.tour.Reponsitoty.TourChiphiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/tourchiphi")
public class TourChiPhiController {
    @Autowired
    TourChiphiRepository tourChiphiRepository;

    @GetMapping("")
    List<TourChiphiEntity> getAll(){
        return tourChiphiRepository.findAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<TourChiphiEntity> getById(@PathVariable Integer id){
        Optional<TourChiphiEntity> entity = tourChiphiRepository.findById(id);
        return entity.map(tourChiphiEntity -> new ResponseEntity<>(tourChiphiEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("")
    ResponseEntity<TourChiphiEntity> insert(@RequestBody TourChiphiEntity entity){
        entity.setChiphiTotal(entity.CalTotal());
        TourChiphiEntity result = tourChiphiRepository.save(entity);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    ResponseEntity<TourChiphiEntity> update(@PathVariable Integer id,@RequestBody TourChiphiEntity tour){
        Optional<TourChiphiEntity> entity = tourChiphiRepository.findById(id);
        tour.setChiphiTotal(tour.CalTotal());
        if(entity.isPresent()){
            tour.setId(id);
        }
        return new ResponseEntity<>(tourChiphiRepository.save(tour),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<TourChiphiEntity> delete(@PathVariable Integer id){
        Optional<TourChiphiEntity> entity = tourChiphiRepository.findById(id);
        if(entity.isPresent()){
            tourChiphiRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/doan/{id}")
    public ResponseEntity<List<TourChiphiEntity>> findByDoanId(@PathVariable Integer id){
        List<TourChiphiEntity> list = tourChiphiRepository.findByDoanID(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
