package com.example.adminhttm.service;

import com.example.adminhttm.entities.Invoice;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceService {
    Page<Invoice> findAll(Pageable pageable);

    Invoice retrieve(Integer id);

    Invoice create(Invoice invoice);

    void update(Invoice invoice, Integer id);

    void delete(Integer id);
}
