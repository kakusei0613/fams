package xyz.kakusei.fams.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Department;
import xyz.kakusei.fams.query.DepartmentQueryObject;
import xyz.kakusei.fams.service.IDepartmentService;
import xyz.kakusei.fams.util.RequiredPermission;


@Controller
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;

    @RequiredPermission({"Query Department", "department:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1, 15);
        model.addAttribute("pageResult", new PageInfo<Department>(departmentService.queryAll()));
        return "/department/tables";
    }
    @RequiredPermission({"Department Detail", "department:detail"})
    @GetMapping("/{id}")
    public String queryById(Model model, @PathVariable("id") Byte id) {
        model.addAttribute("department", departmentService.queryById(id));
        return "/department/form";
    }
    @RequiredPermission({"Query Department", "department:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Department> query(DepartmentQueryObject departmentQueryObject) {
        PageHelper.startPage(departmentQueryObject.getPageNum(), departmentQueryObject.getPageSize());
        PageInfo<Department> result = new PageInfo<Department>(departmentService.queryByCriteria(departmentQueryObject));
        return result;
    }
    @RequiredPermission({"Modify Department","department:modify"})
    @GetMapping("/new")
    public String newDepartment(Model model) {
        model.addAttribute("department", new Department());
        return "/department/form";
    }
    @RequiredPermission({"Modify Department","department:modify"})
    @PostMapping("/new")
    public String modify(Department department) {
        departmentService.saveOrUpdate(department);
        return "redirect:/department/tables";
    }
    @RequiredPermission({"Delete Department","department:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Byte id) {
        departmentService.deleteById(id);
        return "redirect:/department/tables";
    }
}
