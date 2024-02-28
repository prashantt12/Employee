package com.seventythree.Employee.Repository;

import com.seventythree.Employee.Model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    public ArrayList<Employee> findByfirstNameStartingWith(String prefix);
    public ArrayList<Employee> findTop10ByOrderBySalaryDesc();
}
