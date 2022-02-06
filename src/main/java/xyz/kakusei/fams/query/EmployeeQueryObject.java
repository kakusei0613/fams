package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class EmployeeQueryObject {
    private String keyword;
    private Byte department;
    private Byte employeeState;
    private Boolean gender;
}
