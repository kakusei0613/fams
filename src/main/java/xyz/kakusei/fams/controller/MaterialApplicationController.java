package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.MaterialApplication;
import xyz.kakusei.fams.entity.Stock;
import xyz.kakusei.fams.query.MaterialApplicationQueryObject;
import xyz.kakusei.fams.service.*;
import xyz.kakusei.fams.util.RequiredPermission;

@Controller
@RequestMapping("/material/application")
public class MaterialApplicationController {

    @Autowired
    private IMaterialApplicationService materialApplicationService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IMaterialTypeService materialTypeService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IStockService stockService;

    @RequiredPermission({"Query material application","materialApplication:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1, 15);
        model.addAttribute("pageResult", new PageInfo<MaterialApplication>());
        return "/material/application/tables";
    }
    @RequiredPermission({"Query material application","materialApplication:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<MaterialApplication> query(MaterialApplicationQueryObject materialApplicationQueryObject) {
        PageHelper.startPage(materialApplicationQueryObject.getPageNum(), materialApplicationQueryObject.getPageSize());
        PageInfo<MaterialApplication> result = new PageInfo<MaterialApplication>(materialApplicationService.queryByCriteria(materialApplicationQueryObject));
        return result;
    }
    @RequiredPermission({"Query material application","materialApplication:query"})
    @GetMapping("/{id}")
    public String queryById(Model model, @PathVariable("id") Long id) {
        model.addAttribute("materialApplication", materialApplicationService.queryById(id));
        return "/material/application/form";
    }
    @RequiredPermission({"Modify material application","materialApplication:modify"})
    @GetMapping("/new")
    public String newMaterialApplication(Model model) {
        model.addAttribute("materialApplication", new MaterialApplication());
        return "/material/application/form";
    }
    @RequiredPermission({"Modify material application","materialApplication:modify"})
    @PostMapping("/new")
    public String saveOrUpdate(MaterialApplication materialApplication) {
        return "redirect:/material/application/tables";
    }
    @RequiredPermission({"Delete material application","materialApplication:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        materialApplicationService.deleteById(id);
        return "redirect:/material/application/tables";
    }
    @GetMapping("/apply/{orderId}")
    public String apply(Model model, @PathVariable("orderId") Long orderId) {
        model.addAttribute("order", orderService.queryById(orderId));
        model.addAttribute("types", materialTypeService.queryAll());
        model.addAttribute("warehouses", warehouseService.queryAll());
        PageHelper.startPage(1,15);
        model.addAttribute("pageResult", new PageInfo<Stock>(stockService.queryAll()));
        return "/material/application/form";
    }
}
