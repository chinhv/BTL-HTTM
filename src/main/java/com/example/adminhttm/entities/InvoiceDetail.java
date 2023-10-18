package com.example.adminhttm.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "invoice_detail")
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "count")
    private Integer count;

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    private Invoice invoice;
}
