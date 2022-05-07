package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import xyz.kakusei.fams.entity.Stock;
import xyz.kakusei.fams.query.StockQueryObject;
import xyz.kakusei.fams.service.IMaterialService;
import xyz.kakusei.fams.service.IMaterialTypeService;
import xyz.kakusei.fams.service.IStockService;
import xyz.kakusei.fams.service.IWarehouseService;
import xyz.kakusei.fams.util.RequiredPermission;
import xyz.kakusei.fams.util.SystemSetting;

@Controller
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private IStockService stockService;

    @Autowired
    private IWarehouseService warehouseService;

    @Autowired
    private IMaterialService materialService;

    @Autowired
    private IMaterialTypeService materialTypeService;

    @Autowired
    private SystemSetting setting;

    @RequiredPermission({"Query Stock","stock:query"})
    @GetMapping("/tables")
    public String tables(Model model) {
        PageHelper.startPage(1, setting.getPageSize());
        model.addAttribute("pageResult", new PageInfo<Stock>(stockService.queryAll()));
        model.addAttribute("types", materialTypeService.queryAll());
        model.addAttribute("warehouses", warehouseService.queryAll());
        return "/stock/tables";
    }
    @RequiredPermission({"Query Stock","stock:query"})
    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Stock> query(StockQueryObject stockQueryObject) {
        PageHelper.startPage(stockQueryObject.getPageNum(), stockQueryObject.getPageSize());
        PageInfo<Stock> result = new PageInfo<Stock>(stockService.queryByCriteria(stockQueryObject));
        return result;
    }
    @RequiredPermission({"Stock Detail","stock:detail"})
    @GetMapping("/{id}")
    public String stock(Model model, @PathVariable("id") Long id) {
        model.addAttribute("stock", stockService.queryById(id));
        model.addAttribute("materials", materialService.queryAll());
        model.addAttribute("warehouses", warehouseService.queryAll());
        return "/stock/form";
    }
    @RequiredPermission({"Delete Stock","stock:delete"})
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        stockService.deleteById(id);
        return "redirect:/stock/tables";
    }
    @RequiredPermission({"Modify Stock","stock:modify"})
    @GetMapping("/new")
    public String newStock(Model model) {
        model.addAttribute("stock", new Stock());
        model.addAttribute("materials", materialService.queryAll());
        model.addAttribute("warehouses", warehouseService.queryAll());
        return "/stock/form";
    }
    @RequiredPermission({"Modify Stock","stock:modify"})
    @PostMapping("/new")
    public String modify(Stock stock) {
        System.out.println(stock);
        stockService.saveOrUpdate(stock);
        return "redirect:/stock/tables";
    }
}
