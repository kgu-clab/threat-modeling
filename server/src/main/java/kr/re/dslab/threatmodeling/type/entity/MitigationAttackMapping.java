package kr.re.dslab.threatmodeling.type.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
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
@IdClass(MitigationAttackMappingId.class)
public class MitigationAttackMapping {

    @Id
    private String mitigationId;

    @Id
    private String attackId;

    public static MitigationAttackMapping of(String mitigationId, String attackId) {
        return MitigationAttackMapping.builder()
                .mitigationId(mitigationId)
                .attackId(attackId)
                .build();
    }

}
