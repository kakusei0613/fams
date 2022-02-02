package xyz.kakusei.fams.entity;

import lombok.Data;

@Data
public class Permission {
    private Integer id;
    private String name;
    private String expression;
}
