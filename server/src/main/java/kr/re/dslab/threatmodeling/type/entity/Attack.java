package kr.re.dslab.threatmodeling.type.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.re.dslab.threatmodeling.type.dto.MitigationAttackAttackDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Attack {

    @Id
    private String attackId;

    private String attackName;

    private String attackType;

    private String attackUrl;

    @Column(columnDefinition = "TEXT")
    private String mappingDescription;

    public static Attack of(MitigationAttackAttackDto mitigationAttackAttackDto) {
        return Attack.builder()
                .attackId(mitigationAttackAttackDto.getAttackId())
                .attackName(mitigationAttackAttackDto.getAttackName())
                .attackType(mitigationAttackAttackDto.getAttackType())
                .attackUrl(mitigationAttackAttackDto.getAttackUrl())
                .mappingDescription(mitigationAttackAttackDto.getMappingDescription())
                .build();
    }

}
