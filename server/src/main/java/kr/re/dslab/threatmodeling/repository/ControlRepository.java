package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.entity.Control;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ControlRepository extends JpaRepository<Control, String>, ControlRepositoryCustom {

}
