package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.TourDiadiemEntity;
import com.lpwanw.tour.Service.TourDiaDiemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/tourdiadiem")
public class TourDiaDiemRestController {
    @Autowired
    private TourDiaDiemService tourDiaDiemService;

    @GetMapping(value = "")
    public List<TourDiadiemEntity> getAll(){
        return tourDiaDiemService.GetAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TourDiadiemEntity> getById(@PathVariable int id){
        Optional<TourDiadiemEntity> tourDiadiem = tourDiaDiemService.GetById(id);
        return tourDiadiem.map(tourDiadiemEntity -> new ResponseEntity<>(tourDiadiemEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public TourDiadiemEntity addOne(@RequestBody TourDiadiemEntity tourDiadiem){
        return tourDiaDiemService.Insert(tourDiadiem);
    }


    @PutMapping("/{id}")
    public ResponseEntity Update( @PathVariable int id, @RequestBody TourDiadiemEntity tourDiadiemEntity){
        Optional<TourDiadiemEntity> tour = tourDiaDiemService.GetById(id);
        if(tour.isPresent()){
            return new ResponseEntity<>(tourDiaDiemService.Update(tourDiadiemEntity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourDiadiemEntity> delete(@PathVariable int id){
        Optional<TourDiadiemEntity> tourDiadiem = tourDiaDiemService.GetById(id);
        if(tourDiadiem.isPresent()){
            tourDiaDiemService.Delete(id);
            return new ResponseEntity<TourDiadiemEntity>(tourDiadiem.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
