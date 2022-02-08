package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class MaterialApplicationQueryObject extends BasicQueryObject {
    private Long employeeId;
    private String employeeName;
    private Long orderId;
    private Long operatorId;
    private String keyword;
}
