package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.Cve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CveRepository extends JpaRepository<Cve, Long> {

    List<Cve> findCvesByPrimaryImpactContaining(String attackId);

    List<Cve> findCvesBySecondaryImpactContaining(String attackId);

    List<Cve> findCvesByExploitationTechniqueContaining(String attackId);

    List<Cve> findCvesByUncategorizedContaining(String attackId);

}
