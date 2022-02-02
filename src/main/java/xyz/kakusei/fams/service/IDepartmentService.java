package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Department;

import java.util.List;

public interface IDepartmentService {
    List<Department> queryAll();
    Department queryById(Byte id);
    void saveOrUpdate(Department department);
    void deleteById(Byte id);
}
