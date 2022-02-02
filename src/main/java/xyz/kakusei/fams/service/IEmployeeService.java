package xyz.kakusei.fams.service;

import com.github.pagehelper.Page;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.query.EmployeeQueryObject;

import java.util.List;

public interface IEmployeeService {
    List<Employee> queryAll();
    Employee queryByEmployeeId(Long id);
    void saveOrUpdate(Employee employee);
    void deleteByEmployeeId(Long id);
    List<Employee> queryByCriteria(EmployeeQueryObject employeeQueryObject);
}
