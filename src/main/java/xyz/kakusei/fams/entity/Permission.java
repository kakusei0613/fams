package xyz.kakusei.fams.entity;

import lombok.Data;

@Data
public class Permission {
    private Byte id;
    private String name;
    private String expression;
}
