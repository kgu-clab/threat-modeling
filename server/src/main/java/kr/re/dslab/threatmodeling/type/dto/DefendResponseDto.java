package kr.re.dslab.threatmodeling.type.dto;

import kr.re.dslab.threatmodeling.type.entity.Defend;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DefendResponseDto {

    private String defendId;

    private String defendName;

    public static List<DefendResponseDto> of(List<Defend> defends) {
        return defends.stream()
                .map(DefendResponseDto::of)
                .toList();
    }

    public static DefendResponseDto of(Defend defend) {
        return DefendResponseDto.builder()
                .defendId(defend.getDefendId())
                .defendName(defend.getDefendName())
                .build();
    }

}
