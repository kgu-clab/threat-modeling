package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.repository.ControlRepository;
import kr.re.dslab.threatmodeling.type.dto.ControlResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControlService {

    private final ControlRepository controlRepository;

    public List<ControlResponseDto> getControlByAttackId(String attackId) {
        return controlRepository.findControlsByAttackId(attackId);
    }

}
