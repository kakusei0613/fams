package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.entity.OrderStateChange;
import xyz.kakusei.fams.query.OrderQueryObject;
import xyz.kakusei.fams.service.ICustomerService;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IOrderService;

@Controller
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IEmployeeService employeeService;
    @Autowired
    private ICustomerService customerService;


    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<Order>(orderService.queryAll()));
        model.addAttribute("states", orderService.queryAllState());
        model.addAttribute("creators", employeeService.queryAll());
        model.addAttribute("customers", customerService.queryAll());
        return "/order/tables";
    }

    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Order> query(OrderQueryObject orderQueryObject) {
        PageHelper.startPage(1,15);
        PageInfo<Order> result = new PageInfo<Order>(orderService.queryByCriteria(orderQueryObject));
        return result;
    }

    @GetMapping("/{id}")
    public String order(Model model,@PathVariable("id") Long id) {
        model.addAttribute("order", orderService.queryById(id));
        model.addAttribute("customers", customerService.queryAll());
        model.addAttribute("states", orderService.queryAllState());
        PageHelper.startPage(1,5);
        model.addAttribute("pageResult", new PageInfo<OrderStateChange>(orderService.queryOrderStateChangeByOrderId(id)));
        return "/order/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        orderService.deleteById(id);
        return "redirect:/order/tables";
    }

    @GetMapping("/new")
    public String newOrder(Model model) {
        model.addAttribute("order", new Order());
        model.addAttribute("states", orderService.queryAllState());
        return "/order/form";
    }

    @PostMapping("/new")
    public String saveOrUpdate(Order order) {
        orderService.saveOrUpdate(order, Long.parseLong("1"));
        return "redirect:/order/tables";
    }
}
