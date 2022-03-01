package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.Department;
import xyz.kakusei.fams.query.GeneralQueryObject;

import java.util.List;

public interface IDepartmentMapper {
    List<Department> queryAll();
    Department queryById(Byte id);
    List<Department> queryByCriteria(GeneralQueryObject departmentQueryObject);
    void update(Department department);
    void insert(Department department);
    void deleteById(Byte id);
}
