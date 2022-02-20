package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.service.IEmployeeService;
import xyz.kakusei.fams.service.IOrderService;
import xyz.kakusei.fams.service.IPermissionService;
import xyz.kakusei.fams.util.LoginException;

import javax.servlet.http.HttpSession;

@Controller
public class CommonController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IOrderService orderService;


    @GetMapping("/login")
    public String login() {
        return "/login";
    }
    @PostMapping("/login")
    public String login(String username, String password) {
        try {
            employeeService.login(username, password);
            return "redirect:/index";
        }
        catch (LoginException e) {
            e.printStackTrace();
            return "redirect:/login";
        }

    }

    @GetMapping("/logout")
    public String logout() {
        employeeService.logout();
        return "redirect:/login";
    }

    @GetMapping("/index")
    public String index(Model model) {
        PageHelper.startPage(1, 15);
        model.addAttribute("completedOrders", new PageInfo<Order>(orderService.queryOrdersCompleted()));
        PageHelper.startPage(1, 15);
        model.addAttribute("incompleteOrders", new PageInfo<Order>(orderService.queryIncompleteOrder()));
        return "/index";
    }

    @PostMapping("/incompleteOrders")
    @ResponseBody
    public PageInfo<Order> queryIncompleteOrders(Integer pageNum) {
        PageHelper.startPage(pageNum, 15);
        PageInfo<Order> result = new PageInfo<Order>(orderService.queryIncompleteOrder());
        return result;
    }

    @GetMapping("/needPermission")
    public String noPermission() {
        return "/error/needPermission";
    }
}
