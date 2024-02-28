package com.seventythree.Employee.Service;

import com.seventythree.Employee.Model.Employee;
import com.seventythree.Employee.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository emprepo;

    //create an employee
    public String createEmployee(@RequestBody Employee employee){
        try{
            emprepo.save(employee);
            return "Employee Successfully created with the ID: " + employee.getEmployeeId();
        }catch(Exception exception){
            return "Error Created the employee with error: "+ exception;
        }
    }

    //getting list of all employees
    public List<Employee> allEmployee()
    {
        try
        {
            return emprepo.findAll();
        }
        catch(Exception exception)
        {
            return new ArrayList<>();
        }
    }

    //getting employee by id
    public Employee employeeById(@PathVariable("id") int id)
    {
        Employee edata = emprepo.findById(id).orElseThrow(()-> new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found."));

        return edata;
    }

    //updating employee
    public Employee updateEmployee(@PathVariable("id") int id, @RequestBody Employee employee)
    {
        Optional<Employee> edata = emprepo.findById(id);
        Employee eobj= edata.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));

        eobj.setFirstName(employee.getFirstName());
        eobj.setLastName(employee.getLastName());
        eobj.setGender(employee.getGender());
        eobj.setSalary(employee.getSalary());
        emprepo.save(eobj);

        return eobj;

    }

    //deleting a user by id
    public String deleteEmployee(@PathVariable("id") int id)
    {
        Optional<Employee> emp = emprepo.findById(id);
        emp.orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee Not Found"));

        emprepo.deleteById(id);

        return "Employee with ID: " + id + " deleted successfully.";
    }

    //finding an Employee using Prefix
    public ArrayList<Employee> empPrefix(@PathVariable("prefix") String prf)
    {
        ArrayList<Employee> emp = emprepo.findByfirstNameStartingWith(prf);
        if(emp.isEmpty()){
            return new ArrayList<>();
        }
        return emp;
    }

    //finding the top 10 employees by salary
    public ArrayList<Employee> topTen(){
        ArrayList<Employee> edata = emprepo.findTop10ByOrderBySalaryDesc();
        if(edata.isEmpty()){
            return new ArrayList<>();
        }
        else{
            return edata;
        }
    }

}

