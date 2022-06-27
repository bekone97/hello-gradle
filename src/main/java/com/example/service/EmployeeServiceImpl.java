package com.example.service;


import com.example.model.EmployeeRepository;
import com.example.model.entity.Employee;
import lombok.RequiredArgsConstructor;
import org.hibernate.Filter;
import org.hibernate.Session;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {
    private final EntityManager entityManager;
    private final EmployeeRepository employeeRepository;

    public List<Employee> findAll(boolean isDeleted) {
        Session session = entityManager.unwrap(Session.class);
        Filter filter = session.enableFilter("deletedEmployeeFilter");
        filter.setParameter("isDeleted",isDeleted);

        var employees= employeeRepository.findAll();
        session.disableFilter("deletedEmployeeFilter");
        return employees;
    }

    @Override
    public void remove(Long id) {
        employeeRepository.deleteById(id);
    }
}
