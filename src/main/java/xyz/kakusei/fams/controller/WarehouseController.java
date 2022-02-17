package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Warehouse;
import xyz.kakusei.fams.query.WarehouseQueryObject;
import xyz.kakusei.fams.service.IWarehouseService;

@Controller
@RequestMapping("/warehouse")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<Warehouse>(warehouseService.queryAll()));
        return "/warehouse/tables";
    }
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Warehouse> query(WarehouseQueryObject warehouseQueryObject) {
        PageHelper.startPage(warehouseQueryObject.getPageNum(), warehouseQueryObject.getPageSize());
        PageInfo<Warehouse> result = new PageInfo<Warehouse>(warehouseService.queryByCriteria(warehouseQueryObject));
        return result;
    }
    @GetMapping("/{id}")
    public String warehouse(Model model, @PathVariable("id") Byte id) {
        model.addAttribute("warehouse", warehouseService.queryById(id));
        return "/warehouse/form";
    }
    @GetMapping("/delete/{id}")
    public String warehouse(@PathVariable("id") Byte id) {
        warehouseService.deleteById(id);
        return "redirect:/warehouse/tables";
    }
    @GetMapping("/new")
    public String newWarehouse(Model model) {
        model.addAttribute("warehouse", new Warehouse());
        return "/warehouse/form";
    }
    @PostMapping("/new")
    public String saveOrUpdate(Warehouse warehouse) {
        warehouseService.saveOrUpdate(warehouse);
        return "redirect:/warehouse/tables";
    }
}
