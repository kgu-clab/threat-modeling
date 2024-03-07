package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.Defend;
import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DefendRepository extends JpaRepository<Defend, String> {

    List<Defend> findByMitigation(Mitigation mitigation);

}
