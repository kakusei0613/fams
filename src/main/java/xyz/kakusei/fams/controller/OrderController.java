package xyz.kakusei.fams.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.entity.MaterialApplication;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.entity.OrderStateChange;
import xyz.kakusei.fams.query.OrderQueryObject;
import xyz.kakusei.fams.service.ICustomerService;
import xyz.kakusei.fams.service.IDepartmentService;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IOrderService;
import xyz.kakusei.fams.util.RequiredPermission;
import xyz.kakusei.fams.util.SystemSetting;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private ICustomerService customerService;
    @Autowired
    private IDepartmentService departmentService;
    @Autowired
    private SystemSetting setting;

    @RequiredPermission({"Query Order","order:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1, setting.getPageSize());
        model.addAttribute("pageResult", new PageInfo<Order>(orderService.queryAll()));
        model.addAttribute("states", orderService.queryAllState());
        model.addAttribute("creators", employeeService.queryAll());
        model.addAttribute("customers", customerService.queryAll());
        return "/order/tables";
    }
    @RequiredPermission({"Query Order","order:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Order> query(OrderQueryObject orderQueryObject) {
        PageHelper.startPage(orderQueryObject.getPageNum(), setting.getPageSize());
        PageInfo<Order> result = new PageInfo<Order>(orderService.queryByCriteria(orderQueryObject));
        return result;
    }
    @RequiredPermission({"Order Detail","order:detail"})
    @PostMapping("/state")
    @ResponseBody
    public PageInfo<OrderStateChange> queryStateChange(Integer pageNum, Long orderId) {
        PageHelper.startPage(pageNum, setting.getPageSize());
        PageInfo<OrderStateChange> result = new PageInfo<OrderStateChange>(orderService.queryOrderStateChangeByOrderId(orderId));
        return result;
    }
    @RequiredPermission({"Order Detail","order:detail"})
    @PostMapping("/departmentEmployee")
    @ResponseBody
    public List<Employee> queryDepartmentEmployee(Byte departmentId) {
        return employeeService.queryByDepartmentId(departmentId);
    }
    @RequiredPermission({"Order Detail","order:detail"})
    @PostMapping("/used")
    @ResponseBody
    public PageInfo<MaterialApplication> queryOrderUsedStock(Integer pageNum, Long orderId) {
        PageHelper.startPage(pageNum, setting.getPageSize());
        PageInfo<MaterialApplication> result = new PageInfo<MaterialApplication>(orderService.queryMaterialUsedByOrderId(orderId));
        return result;
    }
    @RequiredPermission({"Order Detail","order:detail"})
    @GetMapping("/{id}")
    public String order(Model model,@PathVariable("id") Long id) throws JsonProcessingException {
        model.addAttribute("order", orderService.queryById(id));
        model.addAttribute("customers", customerService.queryAll());
        model.addAttribute("states", orderService.queryAllState());
        PageHelper.startPage(1, setting.getPageSize());
        model.addAttribute("pageResult", new PageInfo<OrderStateChange>(orderService.queryOrderStateChangeByOrderId(id)));
        model.addAttribute("staffs", employeeService.queryAll());
        model.addAttribute("departments", departmentService.queryAll());
        PageHelper.startPage(1, setting.getPageSize());
        model.addAttribute("orderStockUsedPageResult", new PageInfo<MaterialApplication>(orderService.queryMaterialUsedByOrderId(id)));
        return "/order/form";
    }
    @RequiredPermission({"Delete Order","order:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return "redirect:/order/tables";
    }
    @RequiredPermission({"Modify Order","order:modify"})
    @GetMapping("/new")
    public String newOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("customers", customerService.queryAll());
        model.addAttribute("states", orderService.queryAllState());
        model.addAttribute("staffs", employeeService.queryAll());
        return "/order/form";
    }
    @RequiredPermission({"Modify Order","order:modify"})
    @PostMapping("/new")
    public String saveOrUpdate(Order order, Long[] staffIds) {
        Employee user = employeeService.getCurrentUserData();
        orderService.saveOrUpdate(order, staffIds , user.getId());
        return "redirect:/order/tables";
    }

    @PostMapping("/incompleteOrders")
    @ResponseBody
    public PageInfo<Order> queryIncompleteOrders(Integer pageNum) {
        PageHelper.startPage(pageNum, setting.getPageSize());
        PageInfo<Order> result = new PageInfo<Order>(orderService.queryIncompleteOrder());
        return result;
    }
}
