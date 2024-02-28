package com.seventythree.Employee.Controller;

import com.seventythree.Employee.Model.Employee;
import com.seventythree.Employee.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/emp")
public class EmployeeController {
    @Autowired
    EmployeeService empser;

    @PostMapping("/create")
    public String createUser(@RequestBody Employee employee)
    {
        return empser.createEmployee(employee);
    }

    @GetMapping("/list")
    public List<Employee> allEmployees(){
       return empser.allEmployee();
    }

    @GetMapping("/employee/{id}")
    public Employee empById(@PathVariable("id") int id){
        return empser.employeeById(id);
    }

    @PutMapping("/update/{id}")
    public Employee updateEmp(@PathVariable("id") int id, @RequestBody Employee emp)
    {
        return empser.updateEmployee(id,emp);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmp(@PathVariable("id") int id)
    {
        return empser.deleteEmployee(id);
    }

    @GetMapping("/{prefix}")
    public ArrayList<Employee> getEmpPrefix(@PathVariable("prefix") String prf)
    {
        return empser.empPrefix(prf);
    }

    @GetMapping("/top")
    public ArrayList<Employee> getTopTen(){return empser.topTen();}

}
