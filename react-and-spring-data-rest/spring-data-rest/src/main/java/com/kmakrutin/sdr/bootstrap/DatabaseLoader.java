package com.kmakrutin.sdr.bootstrap;

import com.kmakrutin.sdr.data.Employee;
import com.kmakrutin.sdr.repo.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public DatabaseLoader(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        employeeRepository.save(new Employee("Frodo", "Baggins", "ring bearer"));
    }
}
