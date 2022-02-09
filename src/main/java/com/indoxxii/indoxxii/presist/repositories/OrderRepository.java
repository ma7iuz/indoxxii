package com.indoxxii.indoxxii.presist.repositories;


import com.indoxxii.indoxxii.presist.models.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Integer>{
    @Query(value = "SELECT max(noinvoice) FROM orders where noinvoice like :prefix%",nativeQuery = true)
    String findMaxNoInvoice(@Param("prefix") String prefix);
}
