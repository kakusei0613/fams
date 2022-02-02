package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Stock;
import xyz.kakusei.fams.query.StockQueryObject;

import java.util.List;

public interface IStockMapper {
    List<Stock> queryAll();
    Stock queryById(@Param("id") Long id);
    void insert(@Param("stock") Stock stock);
    void update(@Param("stock") Stock stock);
    void deleteById(@Param("id") Long id);
    List<Stock> queryByCriteria(StockQueryObject stockQueryObject);
}
