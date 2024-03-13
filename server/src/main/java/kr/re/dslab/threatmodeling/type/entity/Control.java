package kr.re.dslab.threatmodeling.type.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import kr.re.dslab.threatmodeling.type.dto.json.ControlAttackDto;
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
public class Control {

    @Id
    private String controlId;

    private String controlName;

    public static List<Control> of(List<ControlAttackDto> controlAttackDtos) {
        return controlAttackDtos.stream()
                .map(Control::of)
                .toList();
    }

    public static Control of(ControlAttackDto controlAttackDto) {
        return Control.builder()
                .controlId(controlAttackDto.getControlId())
                .controlName(controlAttackDto.getControlName())
                .build();
    }

}
