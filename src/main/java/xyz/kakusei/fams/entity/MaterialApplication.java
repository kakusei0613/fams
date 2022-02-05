package xyz.kakusei.fams.entity;

import lombok.Data;

@Data
public class MaterialApplication {
    private Long id;
    private Order order;
    private Employee employee;
    private Stock stock;
    private Integer quantity;
    private State state;
    private String applicationTime;
    private Employee operator;
    private String operationTime;
    private String comments;
}
