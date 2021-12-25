package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.TourLoaichiphiEntity;
import com.lpwanw.tour.Entity.TourNhanvienEntity;
import com.lpwanw.tour.Service.NhanVienService;
import com.lpwanw.tour.Service.TourLoaiChiPhiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/loaichiphi")
public class TourLoaiChiPhiRestController {
    @Autowired
    private TourLoaiChiPhiService tourLoaiChiPhiService;

    @GetMapping(value = "")
    public List<TourLoaichiphiEntity> findAll(){
        return tourLoaiChiPhiService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourLoaichiphiEntity> getById(@PathVariable int id){
        Optional<TourLoaichiphiEntity> nv = tourLoaiChiPhiService.findById(id);
        return nv.map(TourLoaichiphiEntity -> new ResponseEntity<>(TourLoaichiphiEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Optional<TourLoaichiphiEntity> save(@RequestBody TourLoaichiphiEntity tourLoaichiphiEntity){
        int id = tourLoaiChiPhiService.save(tourLoaichiphiEntity).getId();
        return tourLoaiChiPhiService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourLoaichiphiEntity> update(@PathVariable int id, @RequestBody TourLoaichiphiEntity tourLoaichiphiEntity){
        Optional<TourLoaichiphiEntity> nv = tourLoaiChiPhiService.findById(id);
        if(nv.isPresent()){
            return new ResponseEntity<>(tourLoaiChiPhiService.save(tourLoaichiphiEntity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourLoaichiphiEntity> delete(@PathVariable int id){
        Optional<TourLoaichiphiEntity> nv = tourLoaiChiPhiService.findById(id);
        if(nv.isPresent()){
            tourLoaiChiPhiService.delete(nv.get());
            return new ResponseEntity<>(nv.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
