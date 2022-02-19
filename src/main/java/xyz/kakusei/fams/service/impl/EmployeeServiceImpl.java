package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.entity.Gender;
import xyz.kakusei.fams.entity.Role;
import xyz.kakusei.fams.entity.State;
import xyz.kakusei.fams.mapper.*;
import xyz.kakusei.fams.query.EmployeeQueryObject;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.util.LoginException;

import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    @Autowired
    private IEmployeeMapper employeeMapper;

    @Autowired
    private IRoleMapper roleMapper;

    @Autowired
    private IGenderMapper genderMapper;

    @Autowired
    private IStateMapper stateMapper;

    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    public List<Employee> queryAll() {
        return employeeMapper.queryAll();
    }

    @Override
    public Employee queryById(Long id) {
        return employeeMapper.queryById(id);
    }

    @Override
    public void saveOrUpdate(Employee employee, Byte[] roleIds) {
        if (employee.getId() == null) {
//              对密码进行MD5加密
            employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes(StandardCharsets.UTF_8)));
            employeeMapper.insert(employee);
        }
        else {
//              如果密码修改了，则进行md5加密
            Employee original = employeeMapper.queryById(employee.getId());
            if (!original.getPassword().equals(employee.getPassword())) {
                employee.setPassword(DigestUtils.md5DigestAsHex(employee.getPassword().getBytes(StandardCharsets.UTF_8)));
            }
            employeeMapper.update(employee);
            roleMapper.deleteEmployeeRoleById(employee.getId());
        }
        for (Byte roleId : roleIds) {
            roleMapper.insertEmployeeRoleById(employee.getId(), roleId);
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

    @Override
    public List<Gender> queryAllGender() {
        return genderMapper.queryAll();
    }

    @Override
    public List<State> queryAllState() {
        return stateMapper.queryAll(IStateMapper.EMPLOYEE_STATES_TABLE);
    }

    @Override
    public List<Employee> queryByDepartmentId(Byte departmentId) {
        EmployeeQueryObject employeeQueryObject = new EmployeeQueryObject();
        employeeQueryObject.setDepartment(departmentId);
        return queryByCriteria(employeeQueryObject);
    }

    @Override
    public void login(String username, String password) {
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        Employee user = employeeMapper.queryByUsernameAndPassword(username, password);
        if (user == null) {
            throw new LoginException("Username or password was wrong.");
        }
        else {
            ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpSession session = servletRequestAttributes.getRequest().getSession();
            session.setAttribute("USER_IN_SESSION", user);
            List<String> permissions = permissionMapper.queryEmployeeExpression(user.getId());
            session.setAttribute("EXPRESSION_IN_SESSION", permissions);
        }
    }
}
