package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Permission;
import xyz.kakusei.fams.entity.Role;
import xyz.kakusei.fams.mapper.IPermissionMapper;
import xyz.kakusei.fams.mapper.IRoleMapper;
import xyz.kakusei.fams.query.RoleQueryObject;
import xyz.kakusei.fams.service.IRoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleMapper roleMapper;

    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    public List<Role> queryAll() {
        return roleMapper.queryAll();
    }

    @Override
    public Role queryByRoleId(Byte id) {
        return roleMapper.queryByRoleId(id);
    }

    @Override
    public void saveOrUpdate(Role role, Byte[] permissionIds) {
        if(role.getId() == null) {
            roleMapper.insert(role);
        }
        else {
            roleMapper.update(role);
            permissionMapper.deleteRolePermission(role.getId());
        }
        for (Byte id : permissionIds) {
            permissionMapper.insertRolePermission(role.getId(), id);
        }
    }

    @Override
    public void deleteByRoleId(Byte id) {
        roleMapper.deleteByRoleId(id);
    }

    @Override
    public void deleteEmployeeRoles(Long employeeId) {
        deleteEmployeeRoles(employeeId);
    }

    @Override
    public List<Role> queryByCriteria(RoleQueryObject roleQueryObject) {
        return roleMapper.queryByCriteria(roleQueryObject);
    }
}
