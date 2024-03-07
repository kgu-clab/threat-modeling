package kr.re.dslab.threatmodeling.type.dto;

import kr.re.dslab.threatmodeling.type.entity.Control;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ControlResponseDto {

    private String controlId;

    private String controlName;

    public static List<ControlResponseDto> of(List<Control> controls) {
        return controls.stream()
                .map(ControlResponseDto::of)
                .toList();
    }

    public static ControlResponseDto of(Control control) {
        return ControlResponseDto.builder()
                .controlId(control.getControlId())
                .controlName(control.getControlName())
                .build();
    }

}
