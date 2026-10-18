package com.axway.tnt.spring_boot_app_structure.controllers;

import com.axway.tnt.spring_boot_app_structure.dto.EmployeeDTO;
import com.axway.tnt.spring_boot_app_structure.services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long employeeID) {
        return employeeService.getEmployeeById(employeeID);
    }

    @GetMapping
    public List<EmployeeDTO> getEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO inputEmp){
       return employeeService.createEmployee(inputEmp);
    }

    @PutMapping(path = "/{empId}")
    public EmployeeDTO updateEmployeeById(@PathVariable("empId") Long id, @RequestBody EmployeeDTO employeeDTO) {
        return employeeService.updateEmployeeById(id, employeeDTO);
    }

    @DeleteMapping(path = "/{empId}")
    public void deleteById(@PathVariable("empId") Long id) {
        employeeService.deleteById(id);
    }

    @PatchMapping(path = "/{empId}")
    public EmployeeDTO updatePartialEmployeeById(@PathVariable("empId") Long id, @RequestBody Map<String, Object> updates) {
        return employeeService.updatePartialEmployeeById(id, updates);
    }
}
