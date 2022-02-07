package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.query.EmployeeQueryObject;
import xyz.kakusei.fams.service.IDepartmentService;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IEmployeeStateService;

@Controller
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private IEmployeeStateService employeeStateService;

    @GetMapping("/tables/")
    public String tables(Model model, @PathVariable("pageNum") Integer pageNum, @PathVariable("pageSize") Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        model.addAttribute("pageResult", new PageInfo<Employee>(employeeService.queryAll()));
        model.addAttribute("departments", departmentService.queryAll());
        model.addAttribute("states", employeeStateService.queryAll());
        return "/employee/tables";
    }

    @PostMapping("/query")
    public String query(EmployeeQueryObject employeeQueryObject) {
        System.out.println(employeeQueryObject);
        return "redirect:/employee/tables";
    }
}
