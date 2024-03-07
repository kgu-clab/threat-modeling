package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.Attack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttackRepository extends JpaRepository<Attack, String> {

}
