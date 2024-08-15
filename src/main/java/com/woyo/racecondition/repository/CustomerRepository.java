package com.woyo.racecondition.repository;

import com.woyo.racecondition.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, String> {
}
