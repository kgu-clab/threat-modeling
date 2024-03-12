package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.exception.NotFoundException;
import kr.re.dslab.threatmodeling.repository.DefendRepository;
import kr.re.dslab.threatmodeling.repository.MitigationRepository;
import kr.re.dslab.threatmodeling.type.dto.DefendResponseDto;
import kr.re.dslab.threatmodeling.type.dto.MitigationResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MitigationService {

    private final MitigationRepository mitigationRepository;

    private final DefendRepository defendRepository;

    public MitigationResponseDto getMitigationInfo(String mitigationId) {
        Mitigation mitigation = getMitigationByIdOrThrow(mitigationId);
        List<DefendResponseDto> relatedDefendTechniques = defendRepository.findDefendsByMitigationId(mitigationId);
        return MitigationResponseDto.of(mitigation, relatedDefendTechniques);
    }

    public List<MitigationResponseDto> getMitigationByAttackId(String attackId) {
        return mitigationRepository.findMitigationsByAttackId(attackId).stream()
                .peek(mitigationDto -> {
                    List<DefendResponseDto> defends = defendRepository.findDefendsByMitigationId(mitigationDto.getMitigationId());
                    mitigationDto.setRelatedDefendTechniques(defends);
                })
                .collect(Collectors.toList());
    }

    private Mitigation getMitigationByIdOrThrow(String mitigationId) {
        return mitigationRepository.findById(mitigationId)
                .orElseThrow(() -> new NotFoundException("해당하는 대응 정보가 없습니다."));
    }

}
