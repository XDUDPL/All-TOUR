package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.KhachhangEntity;
import com.lpwanw.tour.Entity.TourLoaiEntity;
import com.lpwanw.tour.Service.TourLoaiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tourloai")
public class TourLoaiRestController {
    @Autowired
    private TourLoaiService  tourLoaiService;

    @GetMapping("")
    public List<TourLoaiEntity> getAll(){
        return tourLoaiService.GetAll();
    }

    @PostMapping("")
    public Optional<TourLoaiEntity> InsertOne (@RequestBody TourLoaiEntity tourLoaiEntity){
        int id = tourLoaiService.Insert(tourLoaiEntity).getId();
        return tourLoaiService.GetById(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity Update( @PathVariable int id, @RequestBody TourLoaiEntity tourLoaiEntity){
        Optional<TourLoaiEntity> tourLoai = tourLoaiService.GetById(id);
        if(tourLoai.isPresent()){
            return new ResponseEntity<>(tourLoaiService.Update(tourLoaiEntity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourLoaiEntity> delete(@PathVariable int id){
        Optional<TourLoaiEntity> tourLoai = tourLoaiService.GetById(id);
        if(tourLoai.isPresent()){
            tourLoaiService.Delete(id);
            return new ResponseEntity<>(tourLoai.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
