package xyz.kakusei.fams.mapper;

import xyz.kakusei.fams.entity.OrderState;

import java.util.List;

public interface IOrderStateMapper {
    List<OrderState> queryAll();
    OrderState queryById(Byte id);
    void insert(OrderState orderState);
    void update(OrderState orderState);
    void deleteById(Byte id);
}
