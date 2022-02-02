package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class EmployeeQueryObject {
    private String name;
    private Byte department;
    private Byte employeeState;
    private String phone;
    private Boolean gender;
}
