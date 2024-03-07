package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.repository.CveRepository;
import kr.re.dslab.threatmodeling.type.dto.CveResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Cve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CveService {

    private final CveRepository cveRepository;

    public List<CveResponseDto> getCveByAttackId(String attackId) {
        List<Cve> primaryImpactCves = findCvesByPrimaryImpactContainsAttackId(attackId);
        List<Cve> secondaryImpactCves = findCvesBySecondaryImpactContainsAttackId(attackId);
        List<Cve> exploitationTechniqueCves = findCvesByExploitationTechniqueContainsAttackId(attackId);
        List<Cve> uncategorizedCves = findCvesByUncategorizedContainsAttackId(attackId);
        return CveResponseDto.of(primaryImpactCves, secondaryImpactCves, exploitationTechniqueCves, uncategorizedCves);
    }

    public List<Cve> findCvesByPrimaryImpactContainsAttackId(String attackId) {
        return cveRepository.findCvesByPrimaryImpactContaining(attackId);
    }

    public List<Cve> findCvesBySecondaryImpactContainsAttackId(String attackId) {
        return cveRepository.findCvesBySecondaryImpactContaining(attackId);
    }

    public List<Cve> findCvesByExploitationTechniqueContainsAttackId(String attackId) {
        return cveRepository.findCvesByExploitationTechniqueContaining(attackId);
    }

    public List<Cve> findCvesByUncategorizedContainsAttackId(String attackId) {
        return cveRepository.findCvesByUncategorizedContaining(attackId);
    }

}
