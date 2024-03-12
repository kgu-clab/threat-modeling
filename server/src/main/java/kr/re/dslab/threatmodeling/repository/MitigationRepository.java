package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MitigationRepository extends JpaRepository<Mitigation, String>, MitigationRepositoryCustom {

}
