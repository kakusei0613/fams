package xyz.kakusei.fams.service;

import com.github.pagehelper.Page;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.entity.Gender;
import xyz.kakusei.fams.entity.State;
import xyz.kakusei.fams.query.EmployeeQueryObject;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IEmployeeService {
    List<Employee> queryAll();
    Employee queryById(Long id);
    void saveOrUpdate(Employee employee, Byte[] roleIds);
    void deleteByEmployeeId(Long id);
    List<Employee> queryByCriteria(EmployeeQueryObject employeeQueryObject);
    List<Gender> queryAllGender();
    List<State> queryAllState();
    List<Employee> queryByDepartmentId(Byte departmentId);
    void login(String username, String password);
    void logout();
    Employee getCurrentUserData();
    void saveProfile(Employee employee);
}
