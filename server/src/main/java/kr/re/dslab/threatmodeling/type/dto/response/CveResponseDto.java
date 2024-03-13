package kr.re.dslab.threatmodeling.type.dto.response;

import kr.re.dslab.threatmodeling.type.entity.Cve;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class CveResponseDto {

    private String cveId;

    private Float cvss;

    private String phase;

    private String description;

    public static List<CveResponseDto> of(List<Cve> primaryImpactCves, List<Cve> secondaryImpactCves, List<Cve> exploitationTechniqueCves, List<Cve> uncategorizedCves) {
        List<CveResponseDto> cveResponseDtos = new ArrayList<>();
        if (!primaryImpactCves.isEmpty()) {
            primaryImpactCves
                    .forEach(cve -> cveResponseDtos.add(
                            CveResponseDto.of(cve, "Primary Impact"))
                    );
        }
        if (!secondaryImpactCves.isEmpty()) {
            secondaryImpactCves
                    .forEach(cve -> cveResponseDtos.add(
                            CveResponseDto.of(cve, "Secondary Impact"))
                    );
        }
        if (!exploitationTechniqueCves.isEmpty()) {
            exploitationTechniqueCves
                    .forEach(cve -> cveResponseDtos.add(
                            CveResponseDto.of(cve, "Exploitation Technique"))
                    );
        }
        if (!uncategorizedCves.isEmpty()) {
            uncategorizedCves
                    .forEach(cve -> cveResponseDtos.add(
                            CveResponseDto.of(cve, "Uncategorized"))
                    );
        }
        return cveResponseDtos;
    }

    public static CveResponseDto of(Cve cve, String description) {
        return CveResponseDto.builder()
                .cveId(cve.getCveId())
                .cvss(cve.getCvss())
                .phase(cve.getPhase())
                .description(description)
                .build();
    }

}
