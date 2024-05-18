package com.example.repository;

import com.example.dto.CustomerOrderDTO;
import com.example.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	@Query(value = "SELECT c.name, COUNT(o) FROM Customer c JOIN c.orders o GROUP BY c.id")
	List<Object[]> findCustomerOrderCount();

	@Query(value = "SELECT NEW com.example.dto.CustomerOrderDTO(c.name, COUNT(o), SUM(o.price)) FROM Customer c JOIN c.orders o GROUP BY c.id")
	List<CustomerOrderDTO> findCustomerOrderCountResponse();
}
