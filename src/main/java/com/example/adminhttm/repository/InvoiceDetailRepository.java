package com.example.adminhttm.repository;

import com.example.adminhttm.entities.InvoiceDetail;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceDetailRepository extends JpaRepository<InvoiceDetail, Integer> {

    @Query(value = "select i from InvoiceDetail i where i.id = ?1")
    Page<InvoiceDetail> getAllById(Integer id, Pageable pageable);

}
