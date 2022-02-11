package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Department;
import xyz.kakusei.fams.query.DepartmentQueryObject;

import java.util.List;

public interface IDepartmentService {
    List<Department> queryAll();
    Department queryById(Byte id);
    List<Department> queryByCriteria(DepartmentQueryObject departmentQueryObject);
    void saveOrUpdate(Department department);
    void deleteById(Byte id);
}
