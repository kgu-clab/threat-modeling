package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.annotation.LogExecutionTime;
import kr.re.dslab.threatmodeling.repository.AttackRepository;
import kr.re.dslab.threatmodeling.type.dto.AttackRelatedResponseDto;
import kr.re.dslab.threatmodeling.type.dto.AttackResponseDto;
import kr.re.dslab.threatmodeling.type.dto.ControlResponseDto;
import kr.re.dslab.threatmodeling.type.dto.CveResponseDto;
import kr.re.dslab.threatmodeling.type.dto.MitigationResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Attack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class AttackCacheService {

    private final ControlService controlService;

    private final CveService cveService;

    private final MitigationService mitigationService;

    private final AttackRepository attackRepository;

    private final CacheManager cacheManager;

    @Async
    @EventListener(ApplicationReadyEvent.class)
    @LogExecutionTime
    public void cacheAllAttackRelatedInfoOnStartup() {
        log.info("Caching all attack related info on startup");
        getAllAttackRelatedInfo();
    }

    public void getAllAttackRelatedInfo() {
        List<String> allAttackIds = attackRepository.findAllAttackIds();
        fetchAndCacheAttackInfo(allAttackIds);
    }

    public List<AttackRelatedResponseDto> fetchAndCacheAttackInfo(List<String> attackIds) {
        List<CompletableFuture<AttackRelatedResponseDto>> futures = attackIds.stream()
                .map(attackId -> CompletableFuture.supplyAsync(() -> {
                    AttackRelatedResponseDto cachedData = getCachedAttackRelatedInfo(attackId);
                    if (cachedData != null) {
                        return cachedData;
                    } else {
                        AttackRelatedResponseDto responseDto = createAttackRelatedResponseDtoUsingId(attackId);
                        if (responseDto != null) {
                            cacheAttackRelatedInfo(attackId, responseDto);
                        }
                        return responseDto;
                    }
                }))
                .toList();

        return futures.stream()
                .map(CompletableFuture::join)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
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

    public AttackRelatedResponseDto createAttackRelatedResponseDtoUsingId(String attackId) {
        Optional<Attack> attackOpt = attackRepository.findById(attackId);
        if (attackOpt.isEmpty()) {
            return null;
        }
        Attack attack = attackOpt.get();
        return createAttackRelatedResponseDto(attack);
    }

    protected AttackRelatedResponseDto createAttackRelatedResponseDto(Attack attack) {
        AttackResponseDto attackResponseDto = AttackResponseDto.of(attack);
        List<ControlResponseDto> controlResponseDtos = controlService.getControlByAttackId(attack.getAttackId());
        List<MitigationResponseDto> mitigationResponseDtos = mitigationService.getMitigationByAttackId(attack.getAttackId());
        List<CveResponseDto> cveResponseDtos = cveService.getCveByAttackId(attack.getAttackId());

        return AttackRelatedResponseDto.of(
                attackResponseDto,
                controlResponseDtos,
                mitigationResponseDtos,
                cveResponseDtos
        );
    }

}
