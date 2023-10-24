package com.example.adminhttm.controller;

import com.example.adminhttm.dto.ViewDto;
import com.example.adminhttm.entities.Product;
import com.example.adminhttm.entities.User;
import com.example.adminhttm.entities.View;
import com.example.adminhttm.service.ProductService;
import com.example.adminhttm.service.UserService;
import com.example.adminhttm.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("view")
public class ViewController {
    @Autowired
    private ViewService viewService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public String doRetrieveAll(ModelMap modelMap,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<View> list = viewService.findAll(pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listV", list.getContent());
        return "admin/list-view";
    }

    @GetMapping("/update-view/{id}")
    public String doRetrieve(ModelMap modelMap,@PathVariable Integer id){
        View view = viewService.retrieve(id);
        List<Product> listP = productService.findAll();
        List<User> list = userService.findAll();
        modelMap.addAttribute("view", view);
        modelMap.addAttribute("listU", list);
        modelMap.addAttribute("listP", listP);
        return "admin/edit-view";
    }

    @GetMapping("/get-create-form")
    public String getCreate(ModelMap modelMap){
        List<Product> listP = productService.findAll();
        List<User> list = userService.findAll();
        modelMap.addAttribute("listU", list);
        modelMap.addAttribute("listP", listP);
        return "admin/add-view";
    }

    @GetMapping("/search")
    public String doSearch(ModelMap modelMap,
                           @RequestParam(name = "keyword", defaultValue = "") String keyword,
                           @RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo)
    {
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<View> list = viewService.doSearch(keyword, pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listV", list.getContent());
        return "admin/list-view";
    }

    @PostMapping("/add")
    public String doCreate(ModelMap modelMap,@ModelAttribute ViewDto viewDto){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        View newView = new View();
        newView.setUser(viewDto.getUser());
        newView.setProduct(viewDto.getProduct());
        newView.setViewDate(LocalDate.parse(viewDto.getViewDate(), formatter));
        viewService.create(newView);
        return "redirect:/view/get-all";
    }

    @PostMapping("/update")
    public String doUpdate(ModelMap modelMap,@ModelAttribute ViewDto view){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        View v = viewService.retrieve(view.getId());
        v.setUser(view.getUser());
        v.setProduct(view.getProduct());
        v.setViewDate(LocalDate.parse(view.getViewDate(), formatter));
        viewService.update(v, v.getId());
        return "redirect:/view/get-all";
    }

    @GetMapping("/delete/{id}")
    public String doDelete(ModelMap modelMap,@PathVariable Integer id){
        viewService.delete(id);
        return "redirect:/view/get-all";
    }

}
