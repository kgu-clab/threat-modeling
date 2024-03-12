package kr.re.dslab.threatmodeling.type.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cve", indexes = {
        @Index(name = "cve_cvss_idx", columnList = "cvss"),
        @Index(name = "cve_primary_impact_idx", columnList = "primaryImpact"),
        @Index(name = "cve_secondary_impact_idx", columnList = "secondaryImpact"),
        @Index(name = "cve_exploitation_technique_idx", columnList = "exploitationTechnique"),
        @Index(name = "cve_uncategorized_idx", columnList = "uncategorized")
})
public class Cve {

    @Id
    private String cveId;

    private Float cvss;

    private String primaryImpact;

    private String secondaryImpact;

    private String exploitationTechnique;

    private String uncategorized;

    private String phase;

}
