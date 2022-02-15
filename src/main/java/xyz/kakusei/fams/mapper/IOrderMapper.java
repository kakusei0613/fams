package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.query.OrderQueryObject;

import java.util.List;

public interface IOrderMapper {
    List<Order> queryAll();
    Order queryById(Long orderId);
    List<Order> queryByCriteria(OrderQueryObject orderQueryObject);
    void insert(Order order);
    void update(Order order);
    void deleteById(@Param("id") Long id);
}
