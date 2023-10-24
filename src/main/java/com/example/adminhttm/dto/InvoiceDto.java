package com.example.adminhttm.dto;

import com.example.adminhttm.entities.InvoiceDetail;
import com.example.adminhttm.entities.User;
import lombok.Data;

import java.util.List;

@Data
public class InvoiceDto {
    private Integer id;
    private User user;
    private String address;
    private String note;
    private String createDate;
    private List<InvoiceDetail> list;
}
