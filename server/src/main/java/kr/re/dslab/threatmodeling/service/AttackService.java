package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.type.dto.response.AttackRelatedResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AttackService {

    private final AttackCacheService attackCacheService;

    public List<AttackRelatedResponseDto> getAttackRelatedInfo(List<String> attackIds) {
        return attackCacheService.fetchAndCacheAttackInfo(attackIds);
    }

}
