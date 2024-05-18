package com.example.service;

import com.example.entity.Employee;
import com.example.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	public Employee getEmployee(int id) {
		Optional<Employee> employee = employeeRepository.findById(id);
		return employee.orElseThrow(() -> new NoSuchElementException("Employee with id =" + id + " not found."));
	}

	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}

	public Employee updateEmployee(int id, Employee updatedEmployee) {
		Employee existingEmployee = employeeRepository.findById(id)
				.orElseThrow(() -> new NoSuchElementException("Employee with id =" + id + " not found."));
		existingEmployee.setName(updatedEmployee.getName());
		existingEmployee.setDeptName(updatedEmployee.getDeptName());
		existingEmployee.setSalary(updatedEmployee.getSalary());
		existingEmployee.setEmailId(updatedEmployee.getEmailId());
		existingEmployee.setAge(updatedEmployee.getAge());
		return employeeRepository.save(existingEmployee);
	}

	public void deleteEmployee(int id) {
		employeeRepository.deleteById(id);
	}

	public List<Employee> filterBySalary(double salary) {
		log.info("Results with JPQL {} ", employeeRepository.findEmployeeWithJPQL(salary));
		log.info("Results with NATIVE SQL {} ", employeeRepository.findEmployeeWithSQL(salary));
		return employeeRepository.findBySalaryGreaterThan(salary);
	}

	public List<Employee> findEmployeesByAgeRange(int minAge, int maxAge) {
		return employeeRepository.findByAgeBetween(minAge, maxAge);
	}

	public Optional<Double> getAverageSalary() {
		return employeeRepository.avgSalary();
	}

	public List<Employee> findEmployeesWithSorting(String field) {
		return employeeRepository.findAll(Sort.by(Sort.Direction.ASC, field));
	}

	public Page<Employee> findEmployeesWithPagination(int pageNumber, int pageSize) {
		return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize));
	}

	public Page<Employee> findEmployeesWithPaginationAndSorting(int pageNumber, int pageSize, String field) {
		return employeeRepository.findAll(PageRequest.of(pageNumber, pageSize)
				.withSort(Sort.by(field)));
	}

}
