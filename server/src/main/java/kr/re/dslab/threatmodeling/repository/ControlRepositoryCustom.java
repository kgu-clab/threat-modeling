package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.dto.ControlResponseDto;

import java.util.List;

public interface ControlRepositoryCustom {

    List<ControlResponseDto> findControlsByAttackId(String attackId);

}