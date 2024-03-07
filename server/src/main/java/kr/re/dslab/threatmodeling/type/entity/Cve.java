package kr.re.dslab.threatmodeling.type.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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
