package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.entity.Material;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.entity.Stock;
import xyz.kakusei.fams.mapper.IMaterialMapper;
import xyz.kakusei.fams.mapper.IPermissionMapper;
import xyz.kakusei.fams.mapper.IRoleMapper;
import xyz.kakusei.fams.query.EmployeeQueryObject;
import xyz.kakusei.fams.query.StockQueryObject;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IOrderService;
import xyz.kakusei.fams.service.IStockService;

import java.util.List;

@RestController
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

    @GetMapping("/order/queryAll")
    public PageInfo<Order> orderQueryAll() {
        PageHelper.startPage(1,10);
        return new PageInfo<Order>(orderService.queryAll());
    }
    @PostMapping("/order/saveOrUpdate")
    public void orderSaveOrUpdate(Order order) {
        orderService.saveOrUpdate(order, (long)1);
    }
    @GetMapping("/mate/query")
    public PageInfo<Material> MateQuery() {
        PageHelper.startPage(1,10);
        return new PageInfo<Material>(materialMapper.queryAll());
    }
    @PostMapping("/mate/modify")
    public void modify(Material material) {
        System.out.println("===========In controller" + material);
        materialMapper.insert(material);
    }
}
