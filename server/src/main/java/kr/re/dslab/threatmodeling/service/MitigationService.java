package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.exception.NotFoundException;
import kr.re.dslab.threatmodeling.repository.DefendRepository;
import kr.re.dslab.threatmodeling.repository.MitigationRepository;
import kr.re.dslab.threatmodeling.type.dto.DefendResponseDto;
import kr.re.dslab.threatmodeling.type.dto.MitigationResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
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

    @Transactional(readOnly = true)
    public List<MitigationResponseDto> getMitigationByAttackId(String attackId) {
        List<MitigationResponseDto> mitigationDtos = mitigationRepository.findMitigationsByAttackId(attackId);

        List<CompletableFuture<MitigationResponseDto>> futureMitigationDtos = mitigationDtos.stream()
                .map(mitigationDto -> CompletableFuture.supplyAsync(() -> {
                    List<DefendResponseDto> defends = defendRepository.findDefendsByMitigationId(mitigationDto.getMitigationId());
                    mitigationDto.setRelatedDefendTechniques(defends);
                    return mitigationDto;
                }))
                .toList();

        return futureMitigationDtos.stream()
                .map(CompletableFuture::join)
                .collect(Collectors.toList());
    }

    private Mitigation getMitigationByIdOrThrow(String mitigationId) {
        return mitigationRepository.findById(mitigationId)
                .orElseThrow(() -> new NotFoundException("해당하는 대응 정보가 없습니다."));
    }

}
