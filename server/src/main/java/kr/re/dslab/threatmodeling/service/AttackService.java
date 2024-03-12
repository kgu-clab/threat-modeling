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

    public List<AttackRelatedResponseDto> getAllAttackRelatedInfo() {
        List<Attack> attacks = attackRepository.findAll();
        return attacks.stream()
                .map(this::getAttackRelatedResponseDto)
                .toList();
    }

    public List<AttackRelatedResponseDto> getAttackRelatedInfo(List<String> attackIds) {
        List<Attack> attacks = attackRepository.findAllById(attackIds);
        return attacks.stream()
                .map(this::getAttackRelatedResponseDto)
                .toList();
    }

    private Attack getAttackByIdOrThrow(String attackId) {
        return attackRepository.findById(attackId)
                .orElseThrow(() -> new NotFoundException("해당하는 공격 정보가 없습니다."));
    }

    private AttackRelatedResponseDto getAttackRelatedResponseDto(Attack attack) {
        String attackId = attack.getAttackId();
        return AttackRelatedResponseDto.of(
                AttackResponseDto.of(attack),
                controlService.getControlByAttackId(attackId),
                mitigationService.getMitigationByAttackId(attackId),
                cveService.getCveByAttackId(attackId)
        );
    }

}
