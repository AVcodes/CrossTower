package com.assignment.CrossTower.repository;

import com.assignment.CrossTower.entity.CustomersData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerDataRepository extends JpaRepository<CustomersData,Long> {
}
