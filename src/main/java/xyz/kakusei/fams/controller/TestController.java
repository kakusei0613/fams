package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.*;
import xyz.kakusei.fams.mapper.*;
import xyz.kakusei.fams.query.MaterialApplicationQueryObject;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IOrderService;
import xyz.kakusei.fams.service.IStockService;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    IPermissionMapper permissionRepository;
    @Autowired
    IRoleMapper roleRepository;
    @Autowired
    IStockService stockService;
    @Autowired
    IEmployeeService employeeService;
    @Autowired
    IOrderService orderService;

    @Autowired
    IMaterialMapper materialMapper;

    @Autowired
    IMaterialApplicationMapper materialApplicationMapper;

    @Autowired
    IStateMapper stateMapper;


    @GetMapping("/emp")
    public String employee() {
        return "/employee/tables";
    }

    @GetMapping("/emp/{id}")
    @ResponseBody
    public Employee empp(@PathVariable("id") Long id) {
        return employeeService.queryById(id);
    }

    @GetMapping("/s/{id}")
    @ResponseBody
    public Stock stock(@PathVariable("id") Long id) {
        return stockService.queryById(id);
    }

    @GetMapping("/use")
    @ResponseBody
    public List<MaterialApplication> qqqq() {
        MaterialApplicationQueryObject m = new MaterialApplicationQueryObject();
        m.setOrderId(Long.parseLong("1"));
        return materialApplicationMapper.queryByCriteria(m);
    }
}
