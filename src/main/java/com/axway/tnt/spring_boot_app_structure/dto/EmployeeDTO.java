package com.axway.tnt.spring_boot_app_structure.dto;

import com.axway.tnt.spring_boot_app_structure.annotations.EmployeeRoleValidation;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeDTO {
    private Long id;
    @NotBlank(message = "name of employee cannot be blank")
    @Size(min = 3, max = 10, message = "Number of chars in name in between the range [1, 10]")
    private String name;
    @Max(value = 80, message = "Age cannot be more than 80")
    @Min(value = 18, message = "Age cannot be less than 18")
    private Integer age;
    @Email(message = "Email should be a valid email")
    private String email;
    @NotBlank(message = "The role can not be blank")
//    @Pattern(regexp = "^(ADMIN|USER)$", message = "The role of employee can be USER or ADMIN")
    @EmployeeRoleValidation
    private String role; // ADMIN, USER
    @JsonProperty("isActive")
    private Boolean isActive;
}
