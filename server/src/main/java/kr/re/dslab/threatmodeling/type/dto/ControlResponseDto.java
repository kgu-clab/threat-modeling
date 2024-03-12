package kr.re.dslab.threatmodeling.type.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ControlResponseDto {

    private String controlId;

    private String controlName;

}
