package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.repository.DefendRepository;
import kr.re.dslab.threatmodeling.type.dto.DefendResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Defend;
import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DefendService {

    private final DefendRepository defendRepository;

    public List<DefendResponseDto> getDefendsByMitigation(Mitigation mitigation) {
        List<Defend> defends = defendRepository.findByMitigation(mitigation);
        if (defends.isEmpty()) {
            return new ArrayList<>();
        }
        return DefendResponseDto.of(defends);
    }

}
