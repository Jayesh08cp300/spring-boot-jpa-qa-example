package com.example.service;

import com.example.dto.CustomerOrderDTO;
import com.example.dto.OrderRequest;
import com.example.entity.Customer;
import com.example.repository.CustomerRepository;
import com.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private OrderRepository orderRepository;

	public Customer createOrder(OrderRequest orderRequest) {
		Customer customer = (Customer) orderRequest.getCustomer();
		customer.getOrders()
				.forEach(c -> c.setCustomer(customer));
		return customerRepository.save(customer);
	}

	public List<Object[]> findCustomerOrderCount() {
		return customerRepository.findCustomerOrderCount();
	}

	public List<CustomerOrderDTO> findCustomerOrderCountResponse() {
		return customerRepository.findCustomerOrderCountResponse();
	}

}
