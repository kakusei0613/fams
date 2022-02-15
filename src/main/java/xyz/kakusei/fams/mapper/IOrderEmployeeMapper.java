package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Employee;

import java.util.List;

public interface IOrderEmployeeMapper {
    List<Employee> queryByOrderId(@Param("orderId") Long orderId);
    void insert(@Param("orderId") Long orderId, @Param("employeeId") Long employeeId);
    void deleteByOrderId(@Param("id") Long id);
}
