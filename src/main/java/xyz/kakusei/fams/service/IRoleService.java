package xyz.kakusei.fams.service;

import xyz.kakusei.fams.entity.Role;

import java.util.List;

public interface IRoleService {
    List<Role> queryAll();
    Role queryByRoleId(Byte id);
    void saveOrUpdate(Role role);
    void deleteByRoleId(Byte id);
    void deleteEmployeeRoles(Long employeeId);
}
