package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.entity.Role;
import xyz.kakusei.fams.mapper.IEmployeeMapper;
import xyz.kakusei.fams.mapper.IRoleMapper;
import xyz.kakusei.fams.query.EmployeeQueryObject;
import xyz.kakusei.fams.service.IEmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeMapper employeeMapper;

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public List<Employee> queryAll() {
        return employeeMapper.queryAll();
    }

    @Override
    public Employee queryByEmployeeId(Long id) {
        return employeeMapper.queryById(id);
    }

    @Override
    public void saveOrUpdate(Employee employee) {
        if (employee.getId() == null) {
            employeeMapper.insert(employee);
            for (Role role : employee.getRoleList()) {
                roleMapper.insertEmployeeRoleById(employee.getId(), role.getId());
            }
        }
        else {
            employeeMapper.update(employee);
            roleMapper.deleteEmployeeRoleById(employee.getId());
            for (Role role : employee.getRoleList()) {
                roleMapper.insertEmployeeRoleById(employee.getId(), role.getId());
            }
        }
    }

    @Override
    public void deleteByEmployeeId(Long id) {
        employeeMapper.deleteById(id);
        roleMapper.deleteEmployeeRoleById(id);
    }

    @Override
    public List<Employee> queryByCriteria(EmployeeQueryObject employeeQueryObject) {
        return employeeMapper.queryByCriteria(employeeQueryObject);
    }
}
