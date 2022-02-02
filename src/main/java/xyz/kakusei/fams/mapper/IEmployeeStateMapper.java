package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.EmployeeState;

import java.util.List;

public interface IEmployeeStateMapper {
    List<EmployeeState> queryAll();
    EmployeeState queryById(Byte id);
    void insert(EmployeeState employeeState);
    void update(EmployeeState employeeState);
    void deleteById(Byte id);
}
