package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class EmployeeOrderQueryObject extends BasicQueryObject{
    private Byte stateId;
    private Byte notEqualId;
    private Long employeeId;
    private String date;
}
