package com.example.adminhttm.dto;

import com.example.adminhttm.entities.Product;
import com.example.adminhttm.entities.User;
import lombok.Data;

@Data
public class ViewDto {
    private Integer id;
    private User user;
    private Product product;
    private String viewDate;
}
