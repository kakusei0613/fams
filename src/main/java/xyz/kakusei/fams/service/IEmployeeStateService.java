package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.EmployeeState;

import java.util.List;

public interface IEmployeeStateService {
    List<EmployeeState> queryAll();
    EmployeeState queryById(Byte id);
    void deleteById(Byte id);
    void saveOrUpdate(EmployeeState employeeState);
}
