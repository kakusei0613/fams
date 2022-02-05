package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.State;

import java.util.List;

public interface IEmployeeStateService {
    List<State> queryAll();
    State queryById(Byte id);
    void deleteById(Byte id);
    void saveOrUpdate(State employeeState);
}
