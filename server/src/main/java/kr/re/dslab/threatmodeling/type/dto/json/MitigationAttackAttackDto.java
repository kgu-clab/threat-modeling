package kr.re.dslab.threatmodeling.type.dto.json;

import lombok.Data;

@Data
public class MitigationAttackAttackDto {

    private String attackId;

    private String attackName;

    private String attackType;

    private String attackUrl;

    private String mappingDescription;

}
