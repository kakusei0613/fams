package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Stock;
import xyz.kakusei.fams.query.StockQueryObject;

import java.util.List;

public interface IStockService {
    List<Stock> queryAll();
    Stock queryById(Long id);
    void saveOrUpdate(Stock stock);
    void deleteById(Long id);
    List<Stock> queryByCriteria(StockQueryObject stockQueryObject);
}
