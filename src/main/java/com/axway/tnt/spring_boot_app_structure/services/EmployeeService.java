package com.axway.tnt.spring_boot_app_structure.services;

import com.axway.tnt.spring_boot_app_structure.dto.EmployeeDTO;
import com.axway.tnt.spring_boot_app_structure.entities.EmployeeEntity;
import com.axway.tnt.spring_boot_app_structure.repos.EmployeeRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private static final Log LOG = LogFactory.getLog(EmployeeService.class);

    private final ModelMapper mapper;
    private final EmployeeRepository employeeRepository;
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper mapper){
        this.employeeRepository = employeeRepository;
        this.mapper = mapper;
    }


    public Optional<EmployeeDTO> getEmployeeById(Long id) {
        return employeeRepository.findById(id).map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class));
    }

    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(employeeEntity -> mapper.map(employeeEntity, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employee) {
        EmployeeEntity savedEmployee = employeeRepository.save(mapper.map(employee, EmployeeEntity.class));
        return mapper.map(savedEmployee, EmployeeDTO.class);
    }

    public EmployeeDTO updateEmployeeById(Long id, EmployeeDTO employee) {
        EmployeeEntity entity = mapper.map(employee, EmployeeEntity.class);
        entity.setId(id);
        EmployeeEntity updatedEntity = employeeRepository.save(entity);
        return mapper.map(updatedEntity, EmployeeDTO.class);
    }

    public boolean deleteById(Long id) {
        employeeRepository.deleteById(id);
        return true;
    }

    public EmployeeDTO updatePartialEmployeeById(Long id, Map<String, Object> updates) {
        boolean exists = employeeRepository.existsById(id);
        if(!exists) {
            LOG.error("Employee Not Found with id: " + id);
            return null;
        }
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();
        updates.forEach((field, value) -> {
            Field fieldToBeUpdate = ReflectionUtils.findField(EmployeeEntity.class, field);
            ReflectionUtils.makeAccessible(fieldToBeUpdate);
            ReflectionUtils.setField(fieldToBeUpdate, employeeEntity, value);
        });
        return mapper.map(employeeRepository.save(employeeEntity), EmployeeDTO.class);
    }
}