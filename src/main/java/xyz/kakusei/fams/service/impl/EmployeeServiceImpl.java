package xyz.kakusei.fams.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

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
        if (employee.getAdmin() == null) {
            employee.setAdmin(false);
        }
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
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        logger.info("IP address:" + sra.getRequest().getRemoteAddr() + " Trying to login by used " + "User:" + username + " password:" + password);
        password = DigestUtils.md5DigestAsHex(password.getBytes(StandardCharsets.UTF_8));
        Employee user = employeeMapper.queryByUsernameAndPassword(username, password);
        if (user == null) {
            logger.error("IP address:" + sra.getRequest().getRemoteAddr() + "Trying to login by used " + "User:" + username + " password:" + password + " Error");
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

    @Override
    public void logout() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        session.removeAttribute("USER_IN_SESSION");
        session.removeAttribute("EXPRESSION_IN_SESSION");
    }

    @Override
    public Employee getCurrentUserData() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpSession session = servletRequestAttributes.getRequest().getSession();
        Employee user = (Employee) session.getAttribute("USER_IN_SESSION");
        if (user == null) {
            throw new LoginException("The user has not logged in to the system.");
        }
        return user;
    }

    public void saveProfile(Employee employee) {
        Employee user = getCurrentUserData();
        if (employee.getId() != user.getId()) {
            throw new RuntimeException("User id unequal.");
        }
        user.setBirthday(employee.getBirthday());
        user.setPhone(employee.getPhone());
        user.setEmail(employee.getEmail());
        user.setPassword(employee.getPassword());
        user.setAddress(employee.getAddress());
        ArrayList<Byte> roleIds = new ArrayList<Byte>();
        for (Role role : user.getRoleList()) {
            roleIds.add(role.getId());
        }
        saveOrUpdate(user, roleIds.toArray(new Byte[roleIds.size()]));
    }
}
