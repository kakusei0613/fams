package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.entity.OrderStateChange;
import xyz.kakusei.fams.entity.State;
import xyz.kakusei.fams.query.OrderQueryObject;

import java.util.List;

public interface IOrderService {
    List<Order> queryAll();
    Order queryById(Long id);
    List<Order> queryByCriteria(OrderQueryObject orderQueryObject);
    void saveOrUpdate(Order order, Long employeeId);
    void deleteById(Long id);
    List<State> queryAllState();
    List<OrderStateChange> queryOrderStateChangeByOrderId(Long id);
}
