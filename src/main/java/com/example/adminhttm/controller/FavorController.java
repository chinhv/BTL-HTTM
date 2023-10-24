package com.example.adminhttm.controller;

import com.example.adminhttm.entities.Favor;
import com.example.adminhttm.service.FavorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
@Controller
@RequestMapping("favor")
public class FavorController {

    @Autowired
    private FavorService favorService;

    @GetMapping("/{id}")
    public String doRetrieve(ModelMap modelMap,@PathVariable Integer id){
        Favor favor = favorService.retrieve(id);
        modelMap.addAttribute("favor", favor);
        return "admin/edit-favor";
    }

    @GetMapping("/get-create-form")
    public String getCreateForm(){
        return "admin/add-favor";
    }

    @GetMapping("/get-all")
    public String doRetrieveAll(ModelMap modelMap,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo - 1 , 5);
        Page<Favor> list = favorService.findAll1(pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listF", list.getContent());
        return "admin/list-favor";
    }

    @GetMapping("/search")
    public String doSearch(ModelMap modelMap,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword,
                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo)
    {
        Pageable pageable = PageRequest.of(pageNo - 1 , 2);
        Page<Favor> list = favorService.doSearch1(keyword, pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listF", list.getContent());
        return "admin/list-favor";
    }

    @PostMapping("/add")
    public String doCreate(ModelMap modelMap, @ModelAttribute("favor") Favor favor){
        Favor favorExist = favorService.findByName(favor.getName());
        if(favorExist == null){
            favorService.create(favor);
            return "redirect:/favor/get-all";
        }else{
            modelMap.addAttribute("message", "Mục đã tồn tại!");
            return "edit-favor";
        }
    }

    @PostMapping("/update")
    public String doUpdate(ModelMap modelMap, @ModelAttribute("favor") Favor favor){
        Favor f = favorService.retrieve(favor.getId());
        f.setName(favor.getName());
        favorService.update(f.getId(), f);
        return "redirect:/favor/get-all";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(ModelMap modelMap,@PathVariable Integer id){
        favorService.delete(id);
        modelMap.addAttribute("message", "Đã xóa");
        return "redirect:/favor/get-all";
    }
//    @GetMapping("{id}")
//    public ResponseEntity<Favor> doRetrieve(@PathVariable Integer id){
//        Favor entity = favorService.retrieve(id);
//        if(entity == null){
//            return new ResponseEntity<>(null, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(entity, HttpStatus.OK);
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<Favor>> doRetrieveAll(){
//        List<Favor> list = favorService.findAll();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @PostMapping()
//    public ResponseEntity<String> doCreate(@RequestBody Favor favor){
//        Favor favorExist = favorService.findByName(favor.getName());
//        if(favorExist != null){
//            return new ResponseEntity<>("Hạng mục đã tồn tại", HttpStatus.OK);
//        }else{
//            favorService.create(favor);
//            return new ResponseEntity<>("Đã tạo mới", HttpStatus.OK);
//        }
//    }
//
//    @PostMapping("search")
//    public ResponseEntity<List<Favor>> doSearch(@RequestParam String keyword){
//        List<Favor> list = favorService.doSearch(keyword);
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @PutMapping()
//    public ResponseEntity<String> doUpdate(@RequestBody Favor favor){
//        Favor f = favorService.retrieve(favor.getId());
//        f.setName(favor.getName());
//        favorService.update(f.getId(), f);
//        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> doDelete(@PathVariable Integer id){
//        favorService.delete(id);
//        return new ResponseEntity<>("Đã xóa", HttpStatus.OK);
//    }
}
