package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.TourChiphiEntity;
import com.lpwanw.tour.Entity.TourNguoidiEntity;
import com.lpwanw.tour.Reponsitoty.TourNguoidiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/nguoidi")
public class TourNguoiDiController {
    @Autowired
    TourNguoidiRepository tourNguoidiRepository;
    @GetMapping("")
    List<TourNguoidiEntity> getAll(){
        return tourNguoidiRepository.findAll();
    }
    @GetMapping("/{id}")
    ResponseEntity<TourNguoidiEntity> getById(@PathVariable Integer id){
        Optional<TourNguoidiEntity> entity = tourNguoidiRepository.findById(id);
        return entity.map(tourChiphiEntity -> new ResponseEntity<>(tourChiphiEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("")
    ResponseEntity<TourNguoidiEntity> insert(@RequestBody TourNguoidiEntity entity){
        TourNguoidiEntity result = tourNguoidiRepository.save(entity);
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
    @PutMapping("/{id}")
    ResponseEntity<TourNguoidiEntity> update(@PathVariable Integer id,@RequestBody TourNguoidiEntity tour){
        Optional<TourNguoidiEntity> entity = tourNguoidiRepository.findById(id);
        if(entity.isPresent()){
            tour.setId(id);
        }
        return new ResponseEntity<>(tourNguoidiRepository.save(tour),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    ResponseEntity<TourNguoidiEntity> delete(@PathVariable Integer id){
        Optional<TourNguoidiEntity> entity = tourNguoidiRepository.findById(id);
        if(entity.isPresent()){
            tourNguoidiRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/doan/{id}")
    public ResponseEntity<List<TourNguoidiEntity>> findByDoanId(@PathVariable Integer id){
        List<TourNguoidiEntity> list = tourNguoidiRepository.findByDoanID(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
