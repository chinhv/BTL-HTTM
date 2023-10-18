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

    @Column(name = "name")
    private String name;

    @Column(name = "img")
    private String imgLink;

    @Column(name = "price")
    private Integer price;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "favor_id")
    private Favor favor;

    @JsonIgnore
    @OneToMany(mappedBy = "product")
    private List<InvoiceDetail> invoiceDetails;
}
