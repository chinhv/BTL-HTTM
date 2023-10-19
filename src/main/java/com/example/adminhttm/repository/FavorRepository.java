package com.example.adminhttm.repository;

import com.example.adminhttm.entities.Favor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FavorRepository extends JpaRepository<Favor, Integer> {
    @Query(value = "select f from Favor f where (?1 is null or f.name like %?1%)")
    List<Favor> doSearch(String keyword);

    Favor findByName(String name);
}
