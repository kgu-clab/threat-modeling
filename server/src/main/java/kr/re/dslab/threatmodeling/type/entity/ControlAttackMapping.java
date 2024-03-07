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
@IdClass(ControlAttackMappingId.class)
public class ControlAttackMapping {

    @Id
    private String controlId;

    @Id
    private String attackId;

    public static ControlAttackMapping of(String controlId, String attackId) {
        return ControlAttackMapping.builder()
                .controlId(controlId)
                .attackId(attackId)
                .build();
    }

}
