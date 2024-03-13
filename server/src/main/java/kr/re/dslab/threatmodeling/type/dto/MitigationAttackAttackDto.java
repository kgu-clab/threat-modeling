package kr.re.dslab.threatmodeling.type.dto;

import lombok.Data;

@Data
public class MitigationAttackAttackDto {

    private String attackId;

    private String attackName;

    private String attackType;

    private String attackUrl;

    private String mappingDescription;

}
