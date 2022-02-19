package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Customer;
import xyz.kakusei.fams.query.CustomerQueryObject;
import xyz.kakusei.fams.service.ICustomerService;
import xyz.kakusei.fams.util.RequiredPermission;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private ICustomerService customerService;

    @RequiredPermission({"Query Customer","customer:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<Customer>(customerService.queryAll()));
        return "/customer/tables";
    }

    @RequiredPermission({"Query Customer","customer:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Customer> query(Model model, CustomerQueryObject customerQueryObject) {
        PageHelper.startPage(customerQueryObject.getPageNum(),customerQueryObject.getPageSize());
        PageInfo<Customer> result = new PageInfo<Customer>(customerService.queryByCriteria(customerQueryObject));
        return result;
    }

    @RequiredPermission({"Customer Detail","customer:detail"})
    @GetMapping("/{id}")
    public String customer(Model model, @PathVariable("id") Integer id) {
        model.addAttribute("customer", customerService.queryById(id));
        return "/customer/form";
    }

    @RequiredPermission({"Delete Customer","customer:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        customerService.deleteById(id);
        return "redirect:/customer/tables";
    }

    @RequiredPermission({"Modify Customer","customer:modify"})
    @GetMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "/customer/form";
    }

    @RequiredPermission({"Modify Customer", "customer:modify"})
    @PostMapping("/new")
    public String saveOrUpdate(Customer customer) {
        customerService.saveOrUpdate(customer);
        return "redirect:/customer/tables";
    }
}
