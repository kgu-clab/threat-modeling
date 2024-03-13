package kr.re.dslab.threatmodeling.type.dto.json;

import kr.re.dslab.threatmodeling.type.entity.Control;
import lombok.Data;

import java.util.List;

@Data
public class ControlAttackDto {

    private String controlId;

    private String controlName;

    private List<ControlAttackAttackDto> relatedAttackTechniques;

    public Control getControl() {
        return Control.builder()
                .controlId(controlId)
                .controlName(controlName)
                .build();
    }

}
