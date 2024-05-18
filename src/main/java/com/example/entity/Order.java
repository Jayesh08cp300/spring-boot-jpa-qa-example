package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
@Table(name = "orders")
public class Order {

	@Id
	private Long id;

	private String name;
	private int qty;
	private double price;

	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
}
