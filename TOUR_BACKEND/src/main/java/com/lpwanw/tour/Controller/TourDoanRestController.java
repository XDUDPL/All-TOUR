package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.TourChiphiEntity;
import com.lpwanw.tour.Entity.TourDoanEntity;
import com.lpwanw.tour.Entity.TourGiaEntity;
import com.lpwanw.tour.Service.TourChiPhiService;
import com.lpwanw.tour.Service.TourDoanService;
import com.lpwanw.tour.Service.TourGiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/doans")
public class TourDoanRestController {
    @Autowired
    TourDoanService tourDoanService;
    @Autowired
    TourGiaService tourGiaService;
    @Autowired
    TourChiPhiService tourChiPhiService;
    @GetMapping("")
    public List<TourDoanEntity> getAll(){
        return tourDoanService.getAll();
    }
    @GetMapping("/{id}")
    public ResponseEntity<TourDoanEntity> getById(@PathVariable  Integer id){
        Optional<TourDoanEntity> entity = tourDoanService.getById(id);
        return entity.map(tourDoanEntity -> new ResponseEntity<>(tourDoanEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @PostMapping("")
    public ResponseEntity<TourDoanEntity> insert(@RequestBody TourDoanEntity entity){
        Optional<TourGiaEntity> gia = tourGiaService.getCurrentPriceByTourId(entity.getTour().getId(),entity.getDoanNgaydi());
        if(gia.isPresent()){
            entity.setGia(gia.get());
        }
        System.out.println(entity.getTour().getTourTen());
        TourChiphiEntity chiphi = new TourChiphiEntity();
        chiphi.setChiphiTotal(new BigDecimal(0));
        chiphi.setChiphiChitiet("[]");
        TourDoanEntity e = tourDoanService.insert(entity);
        chiphi.setDoan(e);
        tourChiPhiService.save(chiphi);
        if(e!=null){
            return new ResponseEntity<>(e,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PutMapping("/{id}")
    public ResponseEntity<TourDoanEntity> upadte(@RequestBody TourDoanEntity tourDoanEntity,@PathVariable Integer id){
        Optional<TourDoanEntity> entity = tourDoanService.getById(id);
        if(entity.isPresent()){
            if(tourDoanEntity.getDoanNgaydi()==null){
                tourDoanEntity.setDoanNgaydi(entity.get().getDoanNgaydi());
            }
            Optional<TourGiaEntity> gia = tourGiaService.getCurrentPriceByTourId(tourDoanEntity.getTour().getId(),tourDoanEntity.getDoanNgaydi());
            if(gia.isPresent())
                tourDoanEntity.setGia(gia.get());
            return new ResponseEntity<>(tourDoanService.update(tourDoanEntity,id),HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<TourDoanEntity> delete(@PathVariable Integer id){
        Optional<TourDoanEntity> entity = tourDoanService.getById(id);
        if(entity.isPresent()){
            tourDoanService.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
