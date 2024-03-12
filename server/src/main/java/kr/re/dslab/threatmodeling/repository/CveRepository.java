package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.Cve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CveRepository extends JpaRepository<Cve, Long> {

    @Query("SELECT c FROM Cve c WHERE c.primaryImpact LIKE %:attackId% OR c.secondaryImpact LIKE %:attackId% OR c.exploitationTechnique LIKE %:attackId% OR c.uncategorized LIKE %:attackId%")
    List<Cve> findCvesByAttackId(String attackId);

}
