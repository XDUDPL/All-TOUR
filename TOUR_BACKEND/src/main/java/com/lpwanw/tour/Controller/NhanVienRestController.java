package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.KhachhangEntity;
import com.lpwanw.tour.Entity.TourNhanvienEntity;
import com.lpwanw.tour.Service.KhachHangService;
import com.lpwanw.tour.Service.NhanVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value="/api/nhanvien")
public class NhanVienRestController {
    @Autowired
    private NhanVienService nhanVienService;

    @GetMapping(value = "")
    public List<TourNhanvienEntity> findAll(){
        return nhanVienService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TourNhanvienEntity> getById(@PathVariable int id){
        Optional<TourNhanvienEntity> nv = nhanVienService.findById(id);
        return nv.map(TourNhanvienEntity -> new ResponseEntity<>(TourNhanvienEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public Optional<TourNhanvienEntity> save(@RequestBody TourNhanvienEntity tourNhanvienEntity){
        int id = nhanVienService.save(tourNhanvienEntity).getId();
        return nhanVienService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TourNhanvienEntity> update(@PathVariable int id, @RequestBody TourNhanvienEntity tourNhanvienEntity){
        Optional<TourNhanvienEntity> nv = nhanVienService.findById(id);
        if(nv.isPresent()){
            return new ResponseEntity<>(nhanVienService.save(tourNhanvienEntity), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TourNhanvienEntity> delete(@PathVariable int id){
        Optional<TourNhanvienEntity> nv = nhanVienService.findById(id);
        if(nv.isPresent()){
            nhanVienService.delete(nv.get());
            return new ResponseEntity<>(nv.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
