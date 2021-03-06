package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class MaterialApplicationQueryObject extends BasicQueryObject {
    private Long employeeId;
    private Long orderId;
    private Byte stateId;
    private String keyword;
}
