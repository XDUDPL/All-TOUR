package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.TourEntity;
import com.lpwanw.tour.Service.TourService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/tour")
public class TourRestController {
    @Autowired
    private TourService tourService;
    @GetMapping(value = "")
    public List<TourEntity> getAll(){
        return tourService.GetAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TourEntity> getById(@PathVariable int id){
        Optional<TourEntity> tour = tourService.GetById(id);
        return tour.map(tourEntity -> new ResponseEntity<>(tourEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public TourEntity addOne(@RequestBody TourEntity tourEntity){
        return tourService.Insert(tourEntity);
    }


    @PutMapping("/{id}")
    public ResponseEntity Update( @PathVariable int id, @RequestBody TourEntity tourEntity){
        Optional<TourEntity> tour = tourService.GetById(id);
        if(tour.isPresent()){
            return new ResponseEntity<>(tourService.Update(tourEntity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourEntity> delete(@PathVariable int id){
        Optional<TourEntity> tour = tourService.GetById(id);
        if(tour.isPresent()){
            tourService.Delete(id);
            return new ResponseEntity<TourEntity>(tour.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
