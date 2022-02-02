package xyz.kakusei.fams.mapper;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Role;

import java.util.List;

public interface IRoleMapper {
    List<Role> queryAll();
    Role queryByRoleId(@Param("id") Byte id);
    List<Role> queryRoleByEmployeeId(@Param("id") Long employeeId);
    void deleteEmployeeRoleById(@Param("employeeId") Long employeeId);
    void insertEmployeeRoleById(@Param("employeeId") Long employeeId, @Param("roleId") Byte roleId);
    void insert(Role role);
    void update(Role role);
    void deleteByRoleId(@Param("id") Byte id);
    void deleteRolePermission(@Param("roleId") Byte roleId);
    void insertRolePermission(@Param("roleId") Byte roleId, @Param("permissionId") Integer permissionId);
}
