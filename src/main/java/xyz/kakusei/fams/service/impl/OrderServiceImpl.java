package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.mapper.IOrderMapper;
import xyz.kakusei.fams.service.IOrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderMapper orderMapper;

    @Override
    public List<Order> queryAll() {
        return orderMapper.queryAll();
    }

    @Override
    public Order queryById(Long id) {
        return orderMapper.queryById(id);
    }

    @Override
    public void saveOrUpdate(Order order, Long employeeId) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        if (order.getId() == null) {
            orderMapper.insert(order);
//            加入记录状态变化
            orderMapper.insertOrderStateChange(order.getId(), new Byte("1"), simpleDateFormat.format(date), employeeId);
        }
        else {
            orderMapper.update(order);
//            记录状态变化
            orderMapper.insertOrderStateChange(order.getId(), order.getState().getId(), simpleDateFormat.format(date), employeeId);
        }
    }

    @Override
    public void deleteById(Long id) {
        orderMapper.deleteById(id);
        orderMapper.deleteOrderStateChangeByOrderId(id);
    }
}
