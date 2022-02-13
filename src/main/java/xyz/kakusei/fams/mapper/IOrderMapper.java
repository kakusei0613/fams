package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Employee;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.entity.OrderStateChange;
import xyz.kakusei.fams.query.OrderQueryObject;

import java.util.List;

public interface IOrderMapper {
    List<Order> queryAll();
    Order queryById(Long orderId);
    List<Employee> queryEmployeeByOrderId(Long id);
    List<Order> queryByCriteria(OrderQueryObject orderQueryObject);
    void insert(Order order);
    void update(Order order);
    void deleteById(@Param("id") Long id);
    List<OrderStateChange> queryOrderStateChangeByOrderId(Long id);
    void deleteOrderStateChangeByOrderId(Long id);
    void insertOrderStateChange(@Param("orderId") Long orderId, @Param("stateId") Byte stateId, @Param("occurrenceTime") String occurrenceTime, @Param("operatorId") Long operatorId);
}
