package kr.re.dslab.threatmodeling.repository;

import kr.re.dslab.threatmodeling.type.dto.response.MitigationResponseDto;

import java.util.List;

public interface MitigationRepositoryCustom {

    List<MitigationResponseDto> findMitigationsByAttackId(String attackId);

}