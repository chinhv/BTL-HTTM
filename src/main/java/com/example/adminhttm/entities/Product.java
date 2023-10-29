package com.example.adminhttm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Table(name = "product")
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "code")
    private String code;

    @Column(name = "name")
    private String name;

    @Column(name = "img")
    private String imgLink;

    @Column(name = "price")
    private String price;

    @Column(name = "description")
    private String description;

//    @ManyToMany
//    @JoinTable(name = "product_favor",
//            joinColumns = @JoinColumn(name = "product_id", insertable = true, updatable = true),
//            inverseJoinColumns = @JoinColumn(name = "favor_id", insertable = true, updatable = true)
//    )
//    private List<Favor> favors;
    @Column(name = "category")
    private String category;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<InvoiceDetail> invoiceDetails;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<View> views;
}
