package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Department;
import xyz.kakusei.fams.mapper.IDepartmentMapper;
import xyz.kakusei.fams.query.DepartmentQueryObject;
import xyz.kakusei.fams.service.IDepartmentService;

import java.util.List;
@Service
public class DepartmentServiceImpl implements IDepartmentService {
    @Autowired
    private IDepartmentMapper departmentMapper;

    @Override
    public List<Department> queryAll() {
        return departmentMapper.queryAll();
    }

    @Override
    public Department queryById(Byte id) {
        return departmentMapper.queryById(id);
    }

    @Override
    public List<Department> queryByCriteria(DepartmentQueryObject departmentQueryObject) {
        return departmentMapper.queryByCriteria(departmentQueryObject);
    }

    @Override
    public void saveOrUpdate(Department department) {
        System.out.println(department);
        if (department.getId() == null) {
            departmentMapper.insert(department);
        }
        else {
            departmentMapper.update(department);
        }
    }

    @Override
    public void deleteById(Byte id) {
        departmentMapper.deleteById(id);
    }
}
