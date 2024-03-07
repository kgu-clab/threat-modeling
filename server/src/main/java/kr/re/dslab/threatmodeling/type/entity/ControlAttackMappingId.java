package kr.re.dslab.threatmodeling.type.entity;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class ControlAttackMappingId implements Serializable {

    private String controlId;

    private String attackId;

}

