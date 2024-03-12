package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.repository.CveRepository;
import kr.re.dslab.threatmodeling.type.dto.CveResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Cve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CveService {

    private final CveRepository cveRepository;

    public List<CveResponseDto> getCveByAttackId(String attackId) {
        List<Cve> cves = cveRepository.findCvesByAttackId(attackId);
        return aggregateCves(cves, attackId);
    }

    private List<CveResponseDto> aggregateCves(List<Cve> cves, String attackId) {
        List<CveResponseDto> cveResponseDtos = new ArrayList<>();
        for (Cve cve : cves) {
            if (cve.getPrimaryImpact() != null && cve.getPrimaryImpact().contains(attackId)) {
                cveResponseDtos.add(CveResponseDto.of(cve, "Primary Impact"));
            }
            if (cve.getSecondaryImpact() != null && cve.getSecondaryImpact().contains(attackId)) {
                cveResponseDtos.add(CveResponseDto.of(cve, "Secondary Impact"));
            }
            if (cve.getExploitationTechnique() != null && cve.getExploitationTechnique().contains(attackId)) {
                cveResponseDtos.add(CveResponseDto.of(cve, "Exploitation Technique"));
            }
            if (cve.getUncategorized() != null && cve.getUncategorized().contains(attackId)) {
                cveResponseDtos.add(CveResponseDto.of(cve, "Uncategorized"));
            }
        }
        return cveResponseDtos;
    }

}
