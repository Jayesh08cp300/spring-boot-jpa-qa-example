package com.example.controller;

import com.example.dto.CustomerOrderDTO;
import com.example.dto.OrderRequest;
import com.example.entity.Customer;
import com.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
	@Autowired
	private OrderService orderService;

	@PostMapping("/addOrder")
	public Customer addOrder(@RequestBody OrderRequest<Customer> orderRequest) {
		return orderService.createOrder(orderRequest);
	}

	@GetMapping("/orderCount")
	public List<Object[]> getCustomerOrderCount() {
		return orderService.findCustomerOrderCount();
	}

	@GetMapping("/orderCount/response")
	public List<CustomerOrderDTO> getCustomerOrderCountResponse() {
		return orderService.findCustomerOrderCountResponse();
	}
}
