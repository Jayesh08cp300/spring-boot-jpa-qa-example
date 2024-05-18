package com.example.repository;

import com.example.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

	@Query(value = "SELECT e FROM Employee e WHERE e.salary > :salary")
	List<Employee> findEmployeeWithJPQL(@Param("salary") double salary);

	@Query(value = "SELECT * FROM Employee WHERE salary > ?1", nativeQuery = true)
	List<Employee> findEmployeeWithSQL(double salary);

	List<Employee> findBySalaryGreaterThan(double salary);

	List<Employee> findByAgeBetween(int min, int max);

	@Query(value = "SELECT AVG(e.salary) FROM Employee e")
	Optional<Double> avgSalary();
}
