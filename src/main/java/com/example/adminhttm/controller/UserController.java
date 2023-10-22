package com.example.adminhttm.controller;

import com.example.adminhttm.dto.LoginDto;
import com.example.adminhttm.entities.Favor;
import com.example.adminhttm.entities.User;
import com.example.adminhttm.service.FavorService;
import com.example.adminhttm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

//@RestController
@Controller
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private FavorService favorService;

    @GetMapping("/get-all")
    public String doRetrieveAll(ModelMap modelMap,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<User> list = userService.findAll1(pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listU", list.getContent());
        return "admin/list-user";
    }

    @GetMapping("/{id}")
    public String doRetrieve(ModelMap modelMap,@PathVariable Integer id){
        User user = userService.retrieve(id);
        modelMap.addAttribute("user", user);
        return "admin/detail-user";
    }

    @PostMapping("/search")
    public String doSearch(ModelMap modelMap,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword,
                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo)
    {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<User> list = userService.doSearch1(keyword, pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listU", list.getContent());
        return "admin/list-user";
    }

    @GetMapping("/get-create-form")
    public String doRetrieveToCreate(ModelMap modelMap,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo - 1 , 5);
        Page<Favor> list = favorService.findAll1(pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listF", list.getContent());
        return "admin/add-user";
    }

    @PostMapping("/add")
    public String doCreate(ModelMap modelMap,@ModelAttribute("user") User user){
        User userExist = userService.findByEmail(user.getEmail());
        if(userExist == null){
            User newUser = new User();
            newUser.setUserName(user.getUserName());
            newUser.setPassword(user.getPassword());
            newUser.setEmail(user.getEmail());
            newUser.setRole(user.getRole());
            newUser.setFavors(user.getFavors());
            newUser.setPhone(user.getPhone());
            newUser.setGender(user.getGender());
            newUser.setAge(user.getAge());
            userService.create(newUser);
            return "redirect:/user/get-all";
        }else{
            modelMap.addAttribute("message", "Người dùng đã tồn tại");
            return "admin/add-user";
        }

    }

    @GetMapping("/update-user/{id}")
    public String doRetrieveToUpdate(ModelMap modelMap,@PathVariable Integer id,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        User user = userService.retrieve(id);
        Pageable pageable = PageRequest.of(pageNo - 1 , 5);
        Page<Favor> list = favorService.findAll1(pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listF", list.getContent());
        modelMap.addAttribute("user", user);
        return "admin/edit-user";
    }

    @PostMapping("/update")
    public String doUpdate(ModelMap modelMap,@ModelAttribute("user") User user){
        User u = userService.retrieve(user.getId());
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setPhone(user.getPhone());
        u.setFavors(user.getFavors());
        userService.update(u.getId(), u);
        modelMap.addAttribute("message", "Cập nhật thành công");
        return "redirect:/user/get-all";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(@PathVariable Integer id){
        userService.delete(id);
        return "redirect:/user/get-all";
    }

//    @PostMapping("login")
//    public ResponseEntity<User> login(@RequestBody LoginDto dto){
//        User userExist = userService.findByEmail(dto.getEmail());
//        if(userExist == null){
//            return new ResponseEntity<>(null, HttpStatus.OK);
//        }else{
//            if(userExist.getPassword().equals(dto.getPassword())){
//                return new ResponseEntity<>(userExist, HttpStatus.OK);
//            }
//        }
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
//
//    @GetMapping("{id}")
//    public ResponseEntity<User> doRetrieve(@PathVariable Integer id){
//        User user = userService.retrieve(id);
//        if(user != null){
//            return new ResponseEntity<>(user, HttpStatus.OK);
//        }
//        return new ResponseEntity<>(null, HttpStatus.OK);
//    }
//
//    @GetMapping()
//    public ResponseEntity<List<User>> doRetrieveAll(){
//        List<User> list = userService.findAll();
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
//
//    @PostMapping("create")
//    public ResponseEntity<String> doCreate(@RequestBody User user){
//        User userExist = userService.findByEmail(user.getEmail());
//        if(userExist == null){
//            User newUser = new User();
//            newUser.setUserName(user.getUserName());
//            newUser.setPassword(user.getPassword());
//            newUser.setEmail(user.getEmail());
//            newUser.setRole(user.getRole());
//            newUser.setFavors(user.getFavors());
//            newUser.setPhone(user.getPhone());
//            userService.create(newUser);
//            return new ResponseEntity<>("Tạo mới thành công", HttpStatus.OK);
//        }else {
//            return new ResponseEntity<>("Người dùng đã tồn tại", HttpStatus.OK);
//        }
//    }
//
//    @PutMapping()
//    public ResponseEntity<String> doUpdate(@RequestBody User user){
//        User u = userService.retrieve(user.getId());
//        u.setUserName(user.getUserName());
//        u.setPassword(user.getPassword());
//        u.setEmail(user.getEmail());
//        u.setRole(user.getRole());
//        u.setPhone(user.getPhone());
//        u.setFavors(user.getFavors());
//        userService.update(u.getId(), u);
//        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
//    }
//
//    @DeleteMapping("{id}")
//    public ResponseEntity<String> doDelete(@PathVariable Integer id){
//        userService.delete(id);
//        return new ResponseEntity<>("Đã xóa", HttpStatus.OK);
//    }
//
//    @GetMapping("search")
//    public ResponseEntity<List<User>> doSearch(@RequestParam String keyword){
//        List<User> list = userService.doSearch(keyword);
//        return new ResponseEntity<>(list, HttpStatus.OK);
//    }
}
