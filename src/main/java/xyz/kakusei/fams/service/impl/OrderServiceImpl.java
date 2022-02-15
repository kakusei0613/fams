package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Order;
import xyz.kakusei.fams.entity.OrderStateChange;
import xyz.kakusei.fams.entity.State;
import xyz.kakusei.fams.mapper.IOrderEmployeeMapper;
import xyz.kakusei.fams.mapper.IOrderStateChangeMapper;
import xyz.kakusei.fams.mapper.IStateMapper;
import xyz.kakusei.fams.mapper.IOrderMapper;
import xyz.kakusei.fams.query.OrderQueryObject;
import xyz.kakusei.fams.service.IOrderService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements IOrderService {

    @Autowired
    private IOrderMapper orderMapper;

    @Autowired
    private IStateMapper stateMapper;

    @Autowired
    private IOrderStateChangeMapper orderStateChangeMapper;

    @Autowired
    private IOrderEmployeeMapper orderEmployeeMapper;

    private static SimpleDateFormat simpleDateFormat;

    static {
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @Override
    public List<Order> queryAll() {
        return orderMapper.queryAll();
    }

    @Override
    public Order queryById(Long id) {
        return orderMapper.queryById(id);
    }

    @Override
    public List<Order> queryByCriteria(OrderQueryObject orderQueryObject) {
        return orderMapper.queryByCriteria(orderQueryObject);
    }

    @Override
    public void saveOrUpdate(Order order, Long[] staffs, Long employeeId) {
        Date date = new Date(System.currentTimeMillis());
        if (order.getId() == null) {
            order.setCreateTime(simpleDateFormat.format(date));
            order.setProcessTime(simpleDateFormat.format(date));
            orderMapper.insert(order);
//            加入记录状态变化
            orderStateChangeMapper.insertOrderStateChange(order.getId(), order.getState().getId(), simpleDateFormat.format(date), employeeId);
        }
        else {
            order.setProcessTime(simpleDateFormat.format(date));
            orderMapper.update(order);
//            记录状态变化
            orderStateChangeMapper.insertOrderStateChange(order.getId(), order.getState().getId(), simpleDateFormat.format(date), employeeId);
            orderEmployeeMapper.deleteByOrderId(order.getId());
        }
//        更新/插入 订单负责员工信息
        for (Long staffId : staffs) {
            orderEmployeeMapper.insert(order.getId(), staffId);
        }
        date = null;
    }

    @Override
    public void deleteById(Long id) {
        orderMapper.deleteById(id);
        orderStateChangeMapper.deleteOrderStateChangeByOrderId(id);
        orderEmployeeMapper.deleteByOrderId(id);
    }

    @Override
    public List<State> queryAllState() {
        return stateMapper.queryAll(IStateMapper.ORDER_STATES_TABLE);
    }

    @Override
    public List<OrderStateChange> queryOrderStateChangeByOrderId(Long id) {
        return orderStateChangeMapper.queryOrderStateChangeByOrderId(id);
    }
}
