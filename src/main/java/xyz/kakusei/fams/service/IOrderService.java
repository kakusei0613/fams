package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Order;

import java.util.List;

public interface IOrderService {
    List<Order> queryAll();
    Order queryById(Long id);
    void saveOrUpdate(Order order, Long employeeId);
    void deleteById(Long id);
}
