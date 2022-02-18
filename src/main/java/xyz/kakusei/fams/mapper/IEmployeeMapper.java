package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.query.EmployeeQueryObject;

import java.util.List;


public interface IEmployeeMapper {
    List<Employee> queryAll();
    Employee queryById(@Param("id") Long id);
    void insert(Employee employee);
    void update(Employee employee);
    void deleteById(@Param("id") Long id);
    List<Employee> queryByCriteria(EmployeeQueryObject employeeQueryObject);
    Employee queryByUsernameAndPassword(@Param("username") String username, @Param("password") String password);
}
