package kr.re.dslab.threatmodeling.type.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class AttackRelatedResponseDto {

    private final AttackResponseDto attack;

    private final List<ControlResponseDto> relatedControls;

    private final List<MitigationResponseDto> relatedMitigations;

    private final List<CveResponseDto> relatedCves;

    public static AttackRelatedResponseDto of(AttackResponseDto attackResponseDto, List<ControlResponseDto> controlResponseDtos, List<MitigationResponseDto> mitigationResponseDtos, List<CveResponseDto> cveResponseDtos) {
        return new AttackRelatedResponseDto(
                attackResponseDto,
                controlResponseDtos,
                mitigationResponseDtos,
                cveResponseDtos
        );
    }

}
