package kr.re.dslab.threatmodeling.type.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import kr.re.dslab.threatmodeling.type.dto.json.MitigationAttackDto;
import kr.re.dslab.threatmodeling.type.dto.json.MitigationDefendDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Mitigation {

    @Id
    private String mitigationId;

    private String mitigationName;

    private String mitigationType;

    private String mitigationUrl;

    @OneToMany(mappedBy = "mitigation")
    private List<Defend> relatedDefendTechniques;

    public static Mitigation of(MitigationAttackDto mitigationAttackDto) {
        return Mitigation.builder()
                .mitigationId(mitigationAttackDto.getMitigationId())
                .mitigationName(mitigationAttackDto.getMitigationName())
                .mitigationType(mitigationAttackDto.getMitigationType())
                .mitigationUrl(mitigationAttackDto.getMitigationUrl())
                .build();
    }

    public static Mitigation of(MitigationDefendDto mitigationDefendDto) {
        return Mitigation.builder()
                .mitigationId(mitigationDefendDto.getMitigationId())
                .build();
    }

}
