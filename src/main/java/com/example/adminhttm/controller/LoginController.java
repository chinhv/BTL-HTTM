package com.example.adminhttm.controller;

import com.example.adminhttm.entities.User;
import com.example.adminhttm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping("/login-page")
    public String getLogin(){
        return "login1";
    }

    @PostMapping("/login")
    public String login(ModelMap modelMap, @RequestParam("email") String email, @RequestParam("password") String password, HttpSession session){
        User userExist = userService.findByEmail(email);
        if(userExist == null){
            modelMap.addAttribute("ERROR", "Không tồn tại người dùng");
            return "login1";
        }else{
            if(userExist.getPassword().equals(password)){
                session.setAttribute("user", userExist.getUserName());
                return "redirect:/favor/get-all";
            }else{
                modelMap.addAttribute("ERROR", "Sai mật khẩu");
                return "login1";
            }
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("user");
        return "login1";
    }

}
