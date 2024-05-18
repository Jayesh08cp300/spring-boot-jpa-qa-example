package com.example.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private String deptName;
	private String emailId;
	private double salary;
	private int age;

	public Employee(String name, String deptName, double salary, String emailId, int age) {
		this.name = name;
		this.deptName = deptName;
		this.salary = salary;
		this.emailId = emailId;
		this.age = age;
	}
}
