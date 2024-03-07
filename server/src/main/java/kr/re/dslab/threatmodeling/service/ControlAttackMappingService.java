package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.repository.ControlAttackMappingRepository;
import kr.re.dslab.threatmodeling.type.entity.ControlAttackMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ControlAttackMappingService {

    private final ControlAttackMappingRepository controlAttackMappingRepository;

    public List<ControlAttackMapping> getControlAttackMappingByAttackId(String attackId) {
        return controlAttackMappingRepository.findControlAttackMappingsByAttackId(attackId);
    }

}
