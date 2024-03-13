package kr.re.dslab.threatmodeling.type.dto.response;

import kr.re.dslab.threatmodeling.type.entity.Attack;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AttackResponseDto {

    private String attackId;

    private String attackName;

    private String attackType;

    private String attackUrl;

    private String mappingDescription;

    public static AttackResponseDto of(Attack attack) {
        return AttackResponseDto.builder()
                .attackId(attack.getAttackId())
                .attackName(attack.getAttackName())
                .attackType(attack.getAttackType())
                .attackUrl(attack.getAttackUrl())
                .mappingDescription(attack.getMappingDescription())
                .build();
    }

}
