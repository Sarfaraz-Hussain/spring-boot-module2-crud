package com.axway.tnt.spring_boot_app_structure.controllers;

import com.axway.tnt.spring_boot_app_structure.dto.EmployeeDTO;
import com.axway.tnt.spring_boot_app_structure.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path = "/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable("id") Long employeeID) {
        Optional<EmployeeDTO> employeeDto = employeeService.getEmployeeById(employeeID);
        return employeeDto
                .map(employeeDTO -> ResponseEntity.ok(employeeDTO))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getEmployees() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @PostMapping
    public ResponseEntity<EmployeeDTO> createEmployee(@RequestBody EmployeeDTO inputEmp){
       EmployeeDTO savedEmployee = employeeService.createEmployee(inputEmp);
       return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
    }

    @PutMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> updateEmployeeById(@PathVariable("empId") Long id, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployeeById(id, employeeDTO));
    }

    @DeleteMapping(path = "/{empId}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("empId") Long id) {
        boolean gotDeleted = employeeService.deleteById(id);
        if(gotDeleted) return ResponseEntity.ok(true);
        return ResponseEntity.notFound().build();
    }

    @PatchMapping(path = "/{empId}")
    public ResponseEntity<EmployeeDTO> updatePartialEmployeeById(@PathVariable("empId") Long id, @RequestBody Map<String, Object> updates) {
        EmployeeDTO empDto = employeeService.updatePartialEmployeeById(id, updates);
        if(empDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(empDto);
    }
}
