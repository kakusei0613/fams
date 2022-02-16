package xyz.kakusei.fams.query;

import lombok.Data;

@Data
public class MaterialQueryObject extends BasicQueryObject{
    private String keyword;
    private Byte typeId;
}
