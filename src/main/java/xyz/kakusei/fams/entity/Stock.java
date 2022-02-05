package xyz.kakusei.fams.entity;

import lombok.Data;

@Data
public class Stock {
    private Long id;
    private Material material;
    private Warehouse warehouse;
    private Integer quantity;
}
