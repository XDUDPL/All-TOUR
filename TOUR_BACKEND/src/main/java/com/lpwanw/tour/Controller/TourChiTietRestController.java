package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.KhachhangEntity;
import com.lpwanw.tour.Entity.TourChitietEntity;
import com.lpwanw.tour.Service.TourChiTietService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/tourchitiet")
public class TourChiTietRestController {
    @Autowired
    TourChiTietService tourChiTietService;
    @GetMapping("/tour/{id}")
    public ResponseEntity<List<TourChitietEntity>> findByTourId(@PathVariable Integer id){
        List<TourChitietEntity> list = tourChiTietService.getByTourId(id);
        if(!list.isEmpty()){
            return new ResponseEntity<>(list, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(list, HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("")
    public List<TourChitietEntity> getAll(){
        return tourChiTietService.GetAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TourChitietEntity> getByID(@PathVariable  Integer id){
        Optional<TourChitietEntity> e = tourChiTietService.GetById(id);
        return e.map(tourChitietEntity -> new ResponseEntity<>(tourChitietEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("")
    public ResponseEntity<TourChitietEntity> save(@RequestBody TourChitietEntity tourChitietEntity){
        Integer id = tourChiTietService.Insert(tourChitietEntity).getId();
        Optional<TourChitietEntity> e = tourChiTietService.GetById(id);
        return e.map(chitietEntity -> new ResponseEntity<>(chitietEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE));
    }
    @PutMapping("/{id}")
    public ResponseEntity<TourChitietEntity> update(@PathVariable Integer id,@RequestBody TourChitietEntity tourChitietEntity){
        Optional<TourChitietEntity> e = tourChiTietService.GetById(id);
        if(e.isPresent()){
            return new ResponseEntity<>(tourChiTietService.Update(tourChitietEntity),HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TourChitietEntity> delete(@PathVariable Integer id){
        Optional<TourChitietEntity> e = tourChiTietService.GetById(id);
        if(e.isPresent()){
            tourChiTietService.Delete(e.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
