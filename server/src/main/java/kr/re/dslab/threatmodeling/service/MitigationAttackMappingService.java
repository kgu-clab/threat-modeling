package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.repository.MitigationAttackMappingRepository;
import kr.re.dslab.threatmodeling.type.entity.MitigationAttackMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MitigationAttackMappingService {

    private final MitigationAttackMappingRepository mitigationAttackMappingRepository;

    public List<MitigationAttackMapping> getMitigationAttackMappingsByAttackId(String attackId) {
        return mitigationAttackMappingRepository.findByAttackId(attackId);
    }

}
