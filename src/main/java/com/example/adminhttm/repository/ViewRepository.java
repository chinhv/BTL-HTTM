package com.example.adminhttm.repository;

import com.example.adminhttm.entities.User;
import com.example.adminhttm.entities.View;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ViewRepository extends JpaRepository<View, Integer> {

    Page<View> findByUser(User user, Pageable pageable);

    @Query(value = "select v from View v where (?1 is null or v.user.userName like %?1% or v.product.name like %?1%)")
    Page<View> doSearch(String keyword, Pageable pageable);
}
