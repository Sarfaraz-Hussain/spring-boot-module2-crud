package com.axway.tnt.spring_boot_app_structure.dto;

public class EmployeeDTO {
    private Long id;
    private String name;
    private Integer age;
    private String email;
    private Boolean isActive;

    // To expose these above attribute of employee we have to define getters and setters
    public EmployeeDTO() {
        // Default constructor
    }

    // parameterized constructor
    public EmployeeDTO(Long id, String name, Integer age, String email, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.email = email;
        this.isActive = isActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}
