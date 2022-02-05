package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.State;

import java.util.List;

public interface IEmployeeStateMapper {
    List<State> queryAll();
    State queryById(Byte id);
    void insert(State employeeState);
    void update(State employeeState);
    void deleteById(Byte id);
}
