package xyz.kakusei.fams.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xyz.kakusei.fams.entity.Stock;
import xyz.kakusei.fams.query.StockQueryObject;
import xyz.kakusei.fams.service.IStockService;

@Controller
public class StockController {

    @Autowired
    private IStockService stockService;

    @GetMapping("/tables")
    public String tables(Model model) {
        return "/stock/tables";
    }

    @PostMapping("/query")
    @ResponseBody
    public PageInfo<Stock> query(StockQueryObject stockQueryObject) {
        PageHelper.startPage(stockQueryObject.getPageNum(), stockQueryObject.getPageSize());
        PageInfo<Stock> result = new PageInfo<Stock>(stockService.queryByCriteria(stockQueryObject));
        return result;
    }

    @GetMapping("/{id}")
    public String stock(Model model, @PathVariable("id") Long id) {
        model.addAttribute("stock", stockService.queryById(id));
        return "/stock/form";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        return "redirect:/stock/tables";
    }
    @GetMapping("/new")
    public String newStock(Model model) {
        return "/stock/form";
    }
    @PostMapping("/new")
    public String modify(Stock stock) {
        return "redirect:/stock/tables";
    }
}
