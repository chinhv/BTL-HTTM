package com.example.adminhttm.controller;

import com.example.adminhttm.dto.LoginDto;
import com.example.adminhttm.entities.User;
import com.example.adminhttm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("login")
    public ResponseEntity<User> login(@RequestBody LoginDto dto){
        User userExist = userService.findByEmail(dto.getEmail());
        if(userExist == null){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }else{
            if(userExist.getPassword().equals(dto.getPassword())){
                return new ResponseEntity<>(userExist, HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<User> doRetrieve(@PathVariable Integer id){
        User user = userService.retrieve(id);
        if(user != null){
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> doRetrieveAll(){
        List<User> list = userService.findAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("create")
    public ResponseEntity<String> doCreate(@RequestBody User user){
        User userExist = userService.findByEmail(user.getEmail());
        if(userExist == null){
            User newUser = new User();
            newUser.setUserName(user.getUserName());
            newUser.setPassword(user.getPassword());
            newUser.setEmail(user.getEmail());
            newUser.setRole(user.getRole());
            newUser.setFavors(user.getFavors());
            newUser.setPhone(user.getPhone());
            userService.create(newUser);
            return new ResponseEntity<>("Tạo mới thành công", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Người dùng đã tồn tại", HttpStatus.OK);
        }
    }

    @PutMapping()
    public ResponseEntity<String> doUpdate(@RequestBody User user){
        User u = userService.retrieve(user.getId());
        u.setUserName(user.getUserName());
        u.setPassword(user.getPassword());
        u.setEmail(user.getEmail());
        u.setRole(user.getRole());
        u.setPhone(user.getPhone());
        u.setFavors(user.getFavors());
        userService.update(u.getId(), u);
        return new ResponseEntity<>("Cập nhật thành công", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> doDelete(@PathVariable Integer id){
        userService.delete(id);
        return new ResponseEntity<>("Đã xóa", HttpStatus.OK);
    }

    @GetMapping("search")
    public ResponseEntity<List<User>> doSearch(@RequestParam String keyword){
        List<User> list = userService.doSearch(keyword);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
