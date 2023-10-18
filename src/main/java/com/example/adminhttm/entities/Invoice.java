package com.example.adminhttm.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "invoice")
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "totalbill")
    private Integer totalBill;

    @Column(name = "note")
    private String note;

    @Column(name = "createdate")
    private LocalDate createDate;

    @JsonIgnore
    @OneToMany(mappedBy = "invoice")
    private List<InvoiceDetail> invoiceDetails;

}
