package kr.re.dslab.threatmodeling.type.dto;

import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class MitigationResponseDto {

    private String mitigationId;

    private String mitigationName;

    private List<DefendResponseDto> relatedDefendTechniques;

    public static MitigationResponseDto of(Mitigation mitigation, List<DefendResponseDto> relatedDefendTechniques) {
        return MitigationResponseDto.builder()
                .mitigationId(mitigation.getMitigationId())
                .mitigationName(mitigation.getMitigationName())
                .relatedDefendTechniques(relatedDefendTechniques)
                .build();
    }

}
