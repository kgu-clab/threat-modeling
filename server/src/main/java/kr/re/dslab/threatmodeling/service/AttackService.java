package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.exception.NotFoundException;
import kr.re.dslab.threatmodeling.repository.AttackRepository;
import kr.re.dslab.threatmodeling.type.dto.AttackRelatedResponseDto;
import kr.re.dslab.threatmodeling.type.dto.AttackResponseDto;
import kr.re.dslab.threatmodeling.type.dto.ControlResponseDto;
import kr.re.dslab.threatmodeling.type.dto.CveResponseDto;
import kr.re.dslab.threatmodeling.type.dto.MitigationResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Attack;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttackService {

    private final MitigationService mitigationService;

    private final ControlService controlService;

    private final CveService cveService;

    private final AttackRepository attackRepository;

    public AttackRelatedResponseDto getAttackRelatedInfo(String attackId) {
        Attack attack = getAttackByIdOrThrow(attackId);
        AttackResponseDto attackResponseDto = AttackResponseDto.of(attack);
        List<ControlResponseDto> controlResponseDtos = controlService.getControlByAttackId(attackId);
        List<MitigationResponseDto> mitigationResponseDtos = mitigationService.getMitigationByAttackId(attackId);
        List<CveResponseDto> cveResponseDtos = cveService.getCveByAttackId(attackId);
        return AttackRelatedResponseDto.of(attackResponseDto, controlResponseDtos, mitigationResponseDtos, cveResponseDtos);
    }

    private Attack getAttackByIdOrThrow(String attackId) {
        return attackRepository.findById(attackId)
                .orElseThrow(() -> new NotFoundException("해당하는 공격 정보가 없습니다."));
    }

}
