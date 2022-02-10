package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.query.EmployeeQueryObject;
import xyz.kakusei.fams.service.IDepartmentService;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IEmployeeStateService;
import xyz.kakusei.fams.service.IRoleService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IEmployeeStateService employeeStateService;
    @Autowired
    private IRoleService roleService;

    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<Employee>(employeeService.queryAll()));
        model.addAttribute("departments", departmentService.queryAll());
        model.addAttribute("states", employeeStateService.queryAll());
        return "/employee/tables";
    }

    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Employee> query(EmployeeQueryObject employeeQueryObject) {
        PageHelper.startPage(employeeQueryObject.getPageNum(),employeeQueryObject.getPageSize());
        PageInfo<Employee> result = new PageInfo<Employee>(employeeService.queryByCriteria(employeeQueryObject));
        return result;
    }

    @GetMapping("/{id}")
    public String employee(Model model, @PathVariable("id") Long id) {
        model.addAttribute("employee", employeeService.queryById(id));
        model.addAttribute("states", employeeStateService.queryAll());
        model.addAttribute("departments", departmentService.queryAll());
        model.addAttribute("roles", roleService.queryAll());
        return "/employee/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        employeeService.deleteByEmployeeId(id);
        return "redirect:/employee/tables";
    }

    @GetMapping("/new")
    public String newEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("states", employeeStateService.queryAll());
        model.addAttribute("departments", departmentService.queryAll());
        model.addAttribute("roles", roleService.queryAll());
        return "/employee/form";
    }

    @PostMapping("/new")
    public String addEmployee(Employee employee) {
        System.out.println(employee);
        return "redirect:/employee/tables";
    }

}
