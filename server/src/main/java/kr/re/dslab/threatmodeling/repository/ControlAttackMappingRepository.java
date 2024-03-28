package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.ControlAttackMapping;
import kr.re.dslab.threatmodeling.type.entity.ControlAttackMappingId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ControlAttackMappingRepository extends JpaRepository<ControlAttackMapping, ControlAttackMappingId> {

}
