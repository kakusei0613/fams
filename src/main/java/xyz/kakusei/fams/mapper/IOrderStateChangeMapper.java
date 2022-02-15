package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.OrderStateChange;

import java.util.List;

public interface IOrderStateChangeMapper {
    List<OrderStateChange> queryOrderStateChangeByOrderId(Long id);
    void deleteOrderStateChangeByOrderId(Long id);
    void insertOrderStateChange(@Param("orderId") Long orderId, @Param("stateId") Byte stateId, @Param("occurrenceTime") String occurrenceTime, @Param("operatorId") Long operatorId);
}
