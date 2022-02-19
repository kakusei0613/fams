package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Role;
import xyz.kakusei.fams.query.RoleQueryObject;

import java.util.List;

public interface IRoleService {
    List<Role> queryAll();
    Role queryByRoleId(Byte id);
    List<Role> queryByCriteria(RoleQueryObject roleQueryObject);
    void saveOrUpdate(Role role, Byte[] permissionIds);
    void deleteByRoleId(Byte id);
    void deleteEmployeeRoles(Long employeeId);
}
