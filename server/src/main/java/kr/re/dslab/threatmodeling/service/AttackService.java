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
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AttackService {

    private final MitigationService mitigationService;

    private final ControlService controlService;

    private final CveService cveService;

    private final AttackRepository attackRepository;

    private final CacheManager cacheManager;

    @Cacheable(value = "attacksAll", unless = "#result.isEmpty()")
    public List<AttackRelatedResponseDto> getAllAttackRelatedInfo() {
        List<String> allAttackIds = attackRepository.findAllAttackIds();
        return fetchAndCacheAttackInfo(allAttackIds);
    }

    public List<AttackRelatedResponseDto> getAttackRelatedInfo(List<String> attackIds) {
        return fetchAndCacheAttackInfo(attackIds);
    }

    private List<AttackRelatedResponseDto> fetchAndCacheAttackInfo(List<String> attackIds) {
        List<AttackRelatedResponseDto> results = new ArrayList<>();
        for (String attackId : attackIds) {
            AttackRelatedResponseDto cachedData = getCachedAttackRelatedInfo(attackId);
            if (cachedData != null) {
                results.add(cachedData);
            } else {
                Attack attack = attackRepository.findById(attackId)
                        .orElse(null);
                if (attack != null) {
                    AttackRelatedResponseDto responseDto = createAttackRelatedResponseDto(attack);
                    results.add(responseDto);
                    cacheAttackRelatedInfo(attackId, responseDto);
                }
            }
        }
        return results;
    }

    private AttackRelatedResponseDto createAttackRelatedResponseDto(Attack attack) {
        String attackId = attack.getAttackId();
        return AttackRelatedResponseDto.of(
                AttackResponseDto.of(attack),
                controlService.getControlByAttackId(attackId),
                mitigationService.getMitigationByAttackId(attackId),
                cveService.getCveByAttackId(attackId)
        );
    }

    private AttackRelatedResponseDto getCachedAttackRelatedInfo(String attackId) {
        Cache cache = cacheManager.getCache("attackRelatedInfo");
        return cache != null ? cache.get(attackId, AttackRelatedResponseDto.class) : null;
    }

    private void cacheAttackRelatedInfo(String attackId, AttackRelatedResponseDto dto) {
        Cache cache = cacheManager.getCache("attackRelatedInfo");
        if (cache != null) {
            cache.put(attackId, dto);
        }
    }

}
