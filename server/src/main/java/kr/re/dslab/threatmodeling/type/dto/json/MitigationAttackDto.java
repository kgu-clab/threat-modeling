package kr.re.dslab.threatmodeling.type.dto.json;

import kr.re.dslab.threatmodeling.type.dto.json.MitigationAttackAttackDto;
import lombok.Data;

import java.util.List;

@Data
public class MitigationAttackDto {

    private String mitigationId;

    private String mitigationName;

    private String mitigationType;

    private String mitigationUrl;

    private List<MitigationAttackAttackDto> relatedAttackTechniques;

}
