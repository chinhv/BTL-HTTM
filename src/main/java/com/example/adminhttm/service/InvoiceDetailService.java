package com.example.adminhttm.service;

import com.example.adminhttm.entities.InvoiceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceDetailService {
    Page<InvoiceDetail> findAll(Integer id, Pageable pageable);

    InvoiceDetail create(InvoiceDetail invoiceDetail);

    InvoiceDetail retrieve(Integer id);

    void update(InvoiceDetail invoiceDetail, Integer id);

    void delete(Integer id);
}
