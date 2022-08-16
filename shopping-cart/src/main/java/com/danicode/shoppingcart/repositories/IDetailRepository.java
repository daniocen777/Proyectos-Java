package com.danicode.shoppingcart.repositories;

import com.danicode.shoppingcart.entities.Detail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IDetailRepository extends JpaRepository<Detail, String> {

    @Transactional(readOnly = true)
    List<Detail> findBySale_Id(String id);
}
