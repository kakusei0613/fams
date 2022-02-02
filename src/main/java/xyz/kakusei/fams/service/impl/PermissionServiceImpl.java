package xyz.kakusei.fams.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.kakusei.fams.entity.Permission;
import xyz.kakusei.fams.mapper.IPermissionMapper;
import xyz.kakusei.fams.service.IPermissionService;

import java.util.List;

@Service
public class PermissionServiceImpl implements IPermissionService {

    @Autowired
    private IPermissionMapper permissionMapper;

    @Override
    public List<Permission> queryByRoleId(Integer id) {
        return permissionMapper.queryByRoleId(id);
    }

    @Override
    public List<Permission> queryAll() {
        return permissionMapper.queryAll();
    }
}
