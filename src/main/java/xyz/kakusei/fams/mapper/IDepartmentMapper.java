package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.Department;

import java.util.List;

public interface IDepartmentMapper {
    List<Department> queryAll();
    Department queryById(Byte id);
    void update(Department department);
    void insert(Department department);
    void deleteById(Byte id);
}
