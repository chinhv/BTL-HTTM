package com.example.adminhttm.controller;

import com.example.adminhttm.dto.InvoiceDto;
import com.example.adminhttm.entities.Invoice;
import com.example.adminhttm.entities.InvoiceDetail;
import com.example.adminhttm.entities.Product;
import com.example.adminhttm.entities.User;
import com.example.adminhttm.service.InvoiceService;
import com.example.adminhttm.service.ProductService;
import com.example.adminhttm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("invoice")
public class InvoiceController {
    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @GetMapping("/get-all")
    public String doRetrieveAll(ModelMap modelMap,@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo){
        Pageable pageable = PageRequest.of(pageNo - 1, 5);
        Page<Invoice> list = invoiceService.findAll(pageable);
        modelMap.addAttribute("totalPage", list.getTotalPages());
        modelMap.addAttribute("currentPage", pageNo);
        modelMap.addAttribute("listI", list.getContent());
        return "admin/list-invoice";
    }

    @GetMapping("/get-create-form")
    public String getCreateForm(ModelMap modelMap){
        List<User> listU = userService.findAll();
        List<Product> listP = productService.findAll();
        modelMap.addAttribute("listU", listU);
        modelMap.addAttribute("listP", listP);
        return "admin/add-invoice";
    }

    @PostMapping("/add")
    public String doCreate(ModelMap modelMap, @ModelAttribute InvoiceDto invoice){
        Invoice newInvoice = new Invoice();
        Invoice saveInvoice = invoiceService.create(newInvoice);
        List<InvoiceDetail> newList = new ArrayList<>();
        for(InvoiceDetail i : invoice.getList()){
            InvoiceDetail newInvoiceDetail = new InvoiceDetail();
            newInvoiceDetail.setInvoice(saveInvoice);
            newInvoiceDetail.setProduct(i.getProduct());
            newInvoiceDetail.setCount(i.getCount());
            newList.add(newInvoiceDetail);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        saveInvoice.setUser(invoice.getUser());
        saveInvoice.setAddress(invoice.getAddress());
        saveInvoice.setNote(invoice.getNote());
        saveInvoice.setCreateDate(LocalDate.parse(invoice.getCreateDate(), formatter));
        saveInvoice.setInvoiceDetails(newList);
        invoiceService.update(saveInvoice, saveInvoice.getId());
        return "redirect:/invoice/get-all";
    }
}
