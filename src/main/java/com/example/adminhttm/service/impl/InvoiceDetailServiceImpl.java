package com.example.adminhttm.service.impl;

import com.example.adminhttm.entities.InvoiceDetail;
import com.example.adminhttm.repository.InvoiceDetailRepository;
import com.example.adminhttm.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InvoiceDetailServiceImpl implements InvoiceDetailService {

    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    @Override
    public Page<InvoiceDetail> findAll(Integer id, Pageable pageable) {
        return invoiceDetailRepository.getAllById(id, pageable);
    }

    @Override
    public InvoiceDetail create(InvoiceDetail invoiceDetail) {
        return invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public InvoiceDetail retrieve(Integer id) {
        return invoiceDetailRepository.findById(id).orElse(null);
    }

    @Override
    public void update(InvoiceDetail invoiceDetail, Integer id) {
        invoiceDetailRepository.save(invoiceDetail);
    }

    @Override
    public void delete(Integer id) {
        invoiceDetailRepository.deleteById(id);
    }
}
