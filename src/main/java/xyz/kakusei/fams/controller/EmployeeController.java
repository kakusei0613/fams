package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.mapper.IGenderMapper;
import xyz.kakusei.fams.mapper.IStateMapper;
import xyz.kakusei.fams.query.EmployeeQueryObject;
import xyz.kakusei.fams.service.IDepartmentService;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IRoleService;
import xyz.kakusei.fams.util.RequiredPermission;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IRoleService roleService;
    @Autowired
    private IStateMapper stateMapper;
    @Autowired
    private IGenderMapper genderMapper;

    @RequiredPermission({"Query Employee","employee:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<Employee>(employeeService.queryAll()));
        model.addAttribute("departments", departmentService.queryAll());
        model.addAttribute("states", stateMapper.queryAll(IStateMapper.EMPLOYEE_STATES_TABLE));
        model.addAttribute("genders", genderMapper.queryAll());
        return "/employee/tables";
    }
    @RequiredPermission({"Query Employee","employee:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Employee> query(EmployeeQueryObject employeeQueryObject) {
        PageHelper.startPage(employeeQueryObject.getPageNum(),employeeQueryObject.getPageSize());
        PageInfo<Employee> result = new PageInfo<Employee>(employeeService.queryByCriteria(employeeQueryObject));
        return result;
    }
    @RequiredPermission({"Employee Detail","employee:detail"})
    @GetMapping("/{id}")
    public String employee(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employee", employeeService.queryById(id));
        model.addAttribute("states", stateMapper.queryAll(IStateMapper.EMPLOYEE_STATES_TABLE));
        model.addAttribute("genders", employeeService.queryAllGender());
        model.addAttribute("departments", departmentService.queryAll());
        model.addAttribute("roles", roleService.queryAll());
        return "/employee/form";
    }
    @RequiredPermission({"Delete Employee","employee:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        employeeService.deleteByEmployeeId(id);
        return "redirect:/employee/tables";
    }

    @RequiredPermission({"Modify Employee","employee:modify"})
    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("states", stateMapper.queryAll(IStateMapper.EMPLOYEE_STATES_TABLE));
        model.addAttribute("genders", employeeService.queryAllGender());
        model.addAttribute("departments", departmentService.queryAll());
        model.addAttribute("roles", roleService.queryAll());
        return "/employee/form";
    }

    @RequiredPermission({"Modify Employee","employee:modify"})
    @PostMapping("/new")
    public String saveOrUpdate(Employee employee, Byte[] roleIds) {
        employeeService.saveOrUpdate(employee, roleIds);
        System.out.println(employee);
        return "redirect:/employee/tables";
    }

    @GetMapping("/edit")
    public String edit(Model model) {
        model.addAttribute("employee",employeeService.getCurrentUserData());
        return "/employee/form";
    }

    @PostMapping("/edit")
    public String edit(Employee employee) {
        employeeService.saveProfile(employee);
        return "redirect:/index";
    }

}
