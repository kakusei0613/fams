package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Permission;

import java.util.List;

public interface IPermissionMapper {
    List<Permission> queryByRoleId(@Param("id") Integer id);
    List<Permission> queryAll();
    List<String> queryAllPermissionExpression();
    void insert(Permission permission);
}
