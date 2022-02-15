package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.State;

import java.util.List;

public interface IStateMapper {
    static final String EMPLOYEE_STATES_TABLE = "employee_states";
    static final String ORDER_STATES_TABLE = "order_states";
    static final String MATERIAL_APPLICATION_STATE_TABLE = "material_application_states";
    List<State> queryAll(@Param("tableName") String tableName);
    State queryById(@Param("tableName") String tableName, @Param("id") Byte id);
    void insert(@Param("tableName") String tableName, @Param("state") State state);
    void update(@Param("tableName") String tableName, @Param("state") State state);
    void deleteById(@Param("tableName") String tableName, @Param("id") Byte id);
}
