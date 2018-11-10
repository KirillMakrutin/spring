package com.kmakrutin.sdr.repo;

import com.kmakrutin.sdr.data.Employee;
import org.springframework.data.repository.CrudRepository;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
