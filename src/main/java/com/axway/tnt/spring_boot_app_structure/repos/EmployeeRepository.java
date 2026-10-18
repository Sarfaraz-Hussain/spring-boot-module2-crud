package com.axway.tnt.spring_boot_app_structure.repos;

import com.axway.tnt.spring_boot_app_structure.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {

}
