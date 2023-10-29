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
import java.util.Map;

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
    public String doCreate(ModelMap modelMap, @ModelAttribute InvoiceDto invoiceDto) {
        // Lấy dữ liệu từ form
        User selectedUser = userService.retrieve(invoiceDto.getUser());
        String address = invoiceDto.getAddress();
        String note = invoiceDto.getNote();
        LocalDate createDate = LocalDate.parse(invoiceDto.getCreateDate());

        System.out.println(invoiceDto);

        // Tạo hóa đơn mới
        Invoice newInvoice = new Invoice();
        Invoice saveInvoice = invoiceService.create(newInvoice);
        saveInvoice.setUser(selectedUser);
        saveInvoice.setAddress(address);
        saveInvoice.setNote(note);
        saveInvoice.setCreateDate(createDate);

        // Tạo danh sách chi tiết hóa đơn
        List<InvoiceDetail> invoiceDetails = new ArrayList<>();
        for (Integer productId : invoiceDto.getProducts()) {
            Product selectedProduct = productService.retrieve(productId);

            InvoiceDetail newInvoiceDetail = new InvoiceDetail();
            newInvoiceDetail.setProduct(selectedProduct);
            newInvoiceDetail.setCount(1);
            newInvoiceDetail.setInvoice(saveInvoice);

            invoiceDetails.add(newInvoiceDetail);
        }
        // Gán danh sách chi tiết hóa đơn vào hóa đơn
        saveInvoice.setInvoiceDetails(invoiceDetails);
        // Lưu hóa đơn và chi tiết hóa đơn vào cơ sở dữ liệu
        invoiceService.update(saveInvoice, saveInvoice.getId());

        return "redirect:/invoice/get-all";
    }
}