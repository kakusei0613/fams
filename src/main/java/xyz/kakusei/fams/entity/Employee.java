package xyz.kakusei.fams.entity;

import lombok.Data;

import java.util.List;

@Data
public class Employee {
    private Long id;
    private String name;
    private String birthday;
    private Boolean gender;
    private String phone;
    private String email;
    private String password;
    private String address;
    private Boolean admin;
    private Department department;
    private List<Role> roleList;
    private String hireDate;
    private EmployeeState employeeState;
}
