package org.example.isdb4c.services;

import org.example.isdb4c.model.Employee;
import org.example.isdb4c.repository.EmployeeRepository;
import org.example.isdb4c.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(@Autowired EmployeeRepository repository) {
        this.employeeRepository = repository;
    }

    public List<Employee> getAllResponsibleEmployees(Integer caseId) {
        return employeeRepository.findAllByCases_Id(caseId);
    }
}
