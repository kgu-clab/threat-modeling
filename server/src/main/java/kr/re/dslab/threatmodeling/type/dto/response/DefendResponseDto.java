package kr.re.dslab.threatmodeling.type.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefendResponseDto {

    private String defendId;

    private String defendName;

    private String defendUrl;

}
