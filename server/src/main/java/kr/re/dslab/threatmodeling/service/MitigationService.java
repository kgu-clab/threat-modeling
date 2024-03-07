package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.exception.NotFoundException;
import kr.re.dslab.threatmodeling.repository.MitigationRepository;
import kr.re.dslab.threatmodeling.type.dto.DefendResponseDto;
import kr.re.dslab.threatmodeling.type.dto.MitigationResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import kr.re.dslab.threatmodeling.type.entity.MitigationAttackMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MitigationService {

    private final DefendService defendService;

    private final MitigationRepository mitigationRepository;

    private final MitigationAttackMappingService mitigationAttackMappingService;

    public MitigationResponseDto getMitigationInfo(String mitigationId) {
        Mitigation mitigation = getMitigationByIdOrThrow(mitigationId);
        List<DefendResponseDto> relatedDefendTechniques = defendService.getDefendsByMitigation(mitigation);
        return MitigationResponseDto.of(mitigation, relatedDefendTechniques);
    }

    public MitigationResponseDto getMitigation(String mitigationId) {
        Mitigation mitigation = getMitigationById(mitigationId);
        if (mitigation == null) {
            return null;
        }
        List<DefendResponseDto> relatedDefendTechniques = defendService.getDefendsByMitigation(mitigation);
        return MitigationResponseDto.of(mitigation, relatedDefendTechniques);
    }

    public List<MitigationResponseDto> getMitigationByAttackId(String attackId) {
        List<MitigationAttackMapping> mitigationAttackMappings = mitigationAttackMappingService.getMitigationAttackMappingsByAttackId(attackId);
        List<Mitigation> mitigations = mitigationAttackMappings.stream()
                .map(mitigationAttackMapping -> {
                    String mitigationId = mitigationAttackMapping.getMitigationId();
                    return mitigationRepository.findById(mitigationId).orElse(null);
                })
                .toList();
        return mitigations.stream()
                .map(mitigation -> getMitigation(mitigation.getMitigationId()))
                .toList();
    }

    public Mitigation getMitigationByIdOrThrow(String mitigationId) {
        return mitigationRepository.findById(mitigationId)
                .orElseThrow(() -> new NotFoundException("해당하는 대응 정보가 없습니다."));
    }

    public Mitigation getMitigationById(String mitigationId) {
        return mitigationRepository.findById(mitigationId).orElse(null);
    }

}
