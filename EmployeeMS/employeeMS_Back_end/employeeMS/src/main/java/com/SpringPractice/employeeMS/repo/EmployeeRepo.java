package com.SpringPractice.employeeMS.repo;

import com.SpringPractice.employeeMS.entitiy.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepo extends JpaRepository<Employee,Integer> {
}
