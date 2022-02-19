package xyz.kakusei.fams.service;

import org.apache.ibatis.annotations.Param;
import xyz.kakusei.fams.entity.Permission;

import java.util.List;

public interface IPermissionService {
    List<Permission> queryByRoleId(Byte id);
    List<Permission> queryAll();
    void reload();
}
