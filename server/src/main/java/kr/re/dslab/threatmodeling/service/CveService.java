package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.repository.CveRepository;
import kr.re.dslab.threatmodeling.type.dto.CveResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Cve;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class CveService {

    private final CveRepository cveRepository;

    public List<CveResponseDto> getCveByAttackId(String attackId) {
        List<Cve> cves = cveRepository.findCvesByAttackId(attackId);
        return aggregateCves(cves, attackId);
    }

    private List<CveResponseDto> aggregateCves(List<Cve> cves, String attackId) {
        return cves.parallelStream()
                .flatMap(cve -> Stream.of(
                        cve.getPrimaryImpact() != null && cve.getPrimaryImpact().contains(attackId) ?
                                CveResponseDto.of(cve, "Primary Impact") : null,
                        cve.getSecondaryImpact() != null && cve.getSecondaryImpact().contains(attackId) ?
                                CveResponseDto.of(cve, "Secondary Impact") : null,
                        cve.getExploitationTechnique() != null && cve.getExploitationTechnique().contains(attackId) ?
                                CveResponseDto.of(cve, "Exploitation Technique") : null,
                        cve.getUncategorized() != null && cve.getUncategorized().contains(attackId) ?
                                CveResponseDto.of(cve, "Uncategorized") : null
                ))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
