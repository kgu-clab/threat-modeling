package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.repository.ControlRepository;
import kr.re.dslab.threatmodeling.type.dto.ControlResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Control;
import kr.re.dslab.threatmodeling.type.entity.ControlAttackMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControlService {

    private final ControlAttackMappingService controlAttackMappingService;

    private final ControlRepository controlRepository;

    public List<ControlResponseDto> getControlByAttackId(String attackId) {
        List<ControlAttackMapping> controlAttackMappings = controlAttackMappingService.getControlAttackMappingByAttackId(attackId);
        List<String> controlIds = controlAttackMappings.stream()
                .map(ControlAttackMapping::getControlId)
                .toList();
        List<Control> controls = controlRepository.findControlsByControlIdIn(controlIds);
        return ControlResponseDto.of(controls);
    }

}
