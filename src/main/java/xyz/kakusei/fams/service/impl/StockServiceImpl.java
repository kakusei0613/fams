package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Stock;
import xyz.kakusei.fams.mapper.IStockMapper;
import xyz.kakusei.fams.query.StockQueryObject;
import xyz.kakusei.fams.service.IStockService;

import java.util.List;

@Service
public class StockServiceImpl implements IStockService {

    @Autowired
    private IStockMapper stockMapper;

    @Override
    public List<Stock> queryAll() {
        return stockMapper.queryAll();
    }

    @Override
    public Stock queryById(Long id) {
        return stockMapper.queryById(id);
    }

    @Override
    public void saveOrUpdate(Stock stock) {
        if (stock.getId() == null) {
            stockMapper.insert(stock);
        }
        else {
            stockMapper.update(stock);
        }
    }

    @Override
    public void deleteById(Long id) {
        stockMapper.deleteById(id);
    }

    @Override
    public List<Stock> queryByCriteria(StockQueryObject stockQueryObject) {
        return stockMapper.queryByCriteria(stockQueryObject);
    }
}
