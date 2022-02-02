package xyz.kakusei.fams.entity;

import lombok.Data;

import java.util.List;

@Data
public class Role {
    private Byte id;
    private String name;
    private String comments;
    private List<Permission> permissionList;
}
