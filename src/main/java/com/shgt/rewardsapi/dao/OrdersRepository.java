package com.shgt.rewardsapi.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shgt.rewardsapi.entites.Orders;

public interface OrdersRepository extends JpaRepository<Orders, Long> {

	List<Orders> findAllByCustomerId(Long customerId);

}
