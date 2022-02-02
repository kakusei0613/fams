package xyz.kakusei.fams.entity;

import lombok.Data;

@Data
public class OrderStateChange {
    private Long orderId;
    private String state;
    private String occurrenceTime;
    private String operator;
}
