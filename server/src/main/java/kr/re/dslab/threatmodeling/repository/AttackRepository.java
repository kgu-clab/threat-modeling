package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.Attack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttackRepository extends JpaRepository<Attack, String> {

    @Query("SELECT a.attackId FROM Attack a")
    List<String> findAllAttackIds();

}
