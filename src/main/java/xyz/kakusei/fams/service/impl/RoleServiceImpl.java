package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Permission;
import xyz.kakusei.fams.entity.Role;
import xyz.kakusei.fams.mapper.IRoleMapper;
import xyz.kakusei.fams.service.IRoleService;

import java.util.List;

@Service
public class RoleServiceImpl implements IRoleService {

    @Autowired
    private IRoleMapper roleMapper;

    @Override
    public List<Role> queryAll() {
        return roleMapper.queryAll();
    }

    @Override
    public Role queryByRoleId(Byte id) {
        return queryByRoleId(id);
    }

    @Override
    public void saveOrUpdate(Role role) {
        if(role.getId() == null) {
            roleMapper.insert(role);
            for (Permission p : role.getPermissionList()) {
                roleMapper.insertRolePermission(role.getId(), p.getId());
            }
        }
        else {
            roleMapper.update(role);
            roleMapper.deleteRolePermission(role.getId());
            for (Permission p : role.getPermissionList()) {
                roleMapper.insertRolePermission(role.getId(), p.getId());
            }
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
}
