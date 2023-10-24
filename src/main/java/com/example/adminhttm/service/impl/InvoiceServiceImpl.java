package com.example.adminhttm.service.impl;

import com.example.adminhttm.entities.Invoice;
import com.example.adminhttm.repository.InvoiceRepository;
import com.example.adminhttm.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public Page<Invoice> findAll(Pageable pageable) {
        return invoiceRepository.findAll(pageable);
    }

    @Override
    public Invoice retrieve(Integer id) {
        return invoiceRepository.findById(id).orElse(null);
    }

    @Override
    public Invoice create(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public void update(Invoice invoice, Integer id) {
        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(Integer id) {
        invoiceRepository.deleteById(id);
    }
}
