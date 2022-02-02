package xyz.kakusei.fams.entity;

import lombok.Data;

@Data
public class Material {
    private Long id;
    private String name;
    private MaterialType type;
    private String parameter;
}
