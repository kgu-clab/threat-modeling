package kr.re.dslab.threatmodeling.type.dto;

import kr.re.dslab.threatmodeling.type.entity.Attack;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttackResponseDto {

    private String attackId;

    private String attackName;

    private String attackType;

    private String mappingDescription;

    public static AttackResponseDto of(Attack attack) {
        return AttackResponseDto.builder()
                .attackId(attack.getAttackId())
                .attackName(attack.getAttackName())
                .attackType(attack.getAttackType())
                .mappingDescription(attack.getMappingDescription())
                .build();
    }

}
