package xyz.kakusei.fams.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Department;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.service.IDepartmentService;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    private IDepartmentService departmentService;
    @GetMapping("/query")
    public List<Department> queryAll() {
        return departmentService.queryAll();
    }
    @GetMapping("/query/{id}")
    public Department queryById(@PathVariable("id") Byte id) {
        return departmentService.queryById(id);
    }
    @PostMapping("/modify")
    public void modify(Department department) {
        departmentService.saveOrUpdate(department);
    }
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable("id") Byte id) {
        departmentService.deleteById(id);
    }
}
