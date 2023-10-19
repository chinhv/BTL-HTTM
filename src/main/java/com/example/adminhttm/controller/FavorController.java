package com.example.adminhttm.controller;

import com.example.adminhttm.entities.Favor;
import com.example.adminhttm.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("favor")
public class FavorController {

    @Autowired
    private FavorService favorService;

    @GetMapping("{id}")
    public ResponseEntity<Favor> doRetrieve(@PathVariable Integer id){
        Favor entity = favorService.retrieve(id);
        if(entity == null){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        return new ResponseEntity<>(entity, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<Favor>> doRetrieveAll(){
        List<Favor> list = favorService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<String> doCreate(@RequestBody Favor favor){
        Favor favorExist = favorService.findByName(favor.getName());
        if(favorExist != null){
            return new ResponseEntity<>("Hạng mục đã tồn tại", HttpStatus.OK);
        }else{
            favorService.create(favor);
            return new ResponseEntity<>("Đã tạo mới", HttpStatus.OK);
        }
    }

    @PostMapping("search")
    public ResponseEntity<List<Favor>> doSearch(@RequestParam String keyword){
        List<Favor> list = favorService.doSearch(keyword);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PutMapping()
    public ResponseEntity<String> doUpdate(@RequestBody Favor favor){
        Favor f = favorService.retrieve(favor.getId());
        f.setName(favor.getName());
        favorService.update(f.getId(), f);
        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> doDelete(@PathVariable Integer id){
        favorService.delete(id);
        return new ResponseEntity<>("Đã xóa", HttpStatus.OK);
    }
}
