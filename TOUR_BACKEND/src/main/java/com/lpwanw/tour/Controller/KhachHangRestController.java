package com.lpwanw.tour.Controller;

import com.lpwanw.tour.Entity.KhachhangEntity;
import com.lpwanw.tour.Service.KhachHangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping(value="/api/khachhang")
public class KhachHangRestController {
        @Autowired
        private KhachHangService khachHangService;

        @GetMapping(value = "")
        public List<KhachhangEntity> findAll(){
                return khachHangService.findAll();
        }

        @GetMapping("/{id}")
        public ResponseEntity<KhachhangEntity> getById(@PathVariable int id){
                Optional<KhachhangEntity> kh = khachHangService.findById(id);
                return kh.map(khachhangEntity -> new ResponseEntity<>(khachhangEntity, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }

        @PostMapping("")
        public Optional<KhachhangEntity> save(@RequestBody KhachhangEntity khachhangEntity){
                int id = khachHangService.save(khachhangEntity).getId();
                return khachHangService.findById(id);
        }

        @PutMapping("/{id}")
        public ResponseEntity<KhachhangEntity> update(@PathVariable int id, @RequestBody KhachhangEntity khachhangEntity){
                Optional<KhachhangEntity> kh = khachHangService.findById(id);
                if(kh.isPresent()){
                        return new ResponseEntity<>(khachHangService.save(khachhangEntity), HttpStatus.OK);
                }else{
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<KhachhangEntity> delete(@PathVariable int id){
                Optional<KhachhangEntity> kh = khachHangService.findById(id);
                if(kh.isPresent()){
                        khachHangService.delete(kh.get());
                        return new ResponseEntity<>(kh.get(), HttpStatus.OK);
                }else{
                        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
        }

}