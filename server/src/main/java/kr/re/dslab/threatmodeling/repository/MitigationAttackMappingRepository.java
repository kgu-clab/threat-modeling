package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.MitigationAttackMapping;
import kr.re.dslab.threatmodeling.type.entity.MitigationAttackMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MitigationAttackMappingRepository extends JpaRepository<MitigationAttackMapping, MitigationAttackMappingId> {

    List<MitigationAttackMapping> findByAttackId(String attackId);

}
