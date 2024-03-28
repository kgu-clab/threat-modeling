package kr.re.dslab.threatmodeling.service;

import kr.re.dslab.threatmodeling.annotation.LogExecutionTime;
import kr.re.dslab.threatmodeling.repository.AttackRepository;
import kr.re.dslab.threatmodeling.type.dto.response.AttackRelatedResponseDto;
import kr.re.dslab.threatmodeling.type.dto.response.AttackResponseDto;
import kr.re.dslab.threatmodeling.type.dto.response.ControlResponseDto;
import kr.re.dslab.threatmodeling.type.dto.response.CveResponseDto;
import kr.re.dslab.threatmodeling.type.dto.response.MitigationResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Attack;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
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

    @Qualifier("taskExecutor")
    private final Executor taskExecutor;

    @Async
    @EventListener(ApplicationReadyEvent.class)
    @LogExecutionTime
    public void cacheAllAttackRelatedInfoOnStartup() {
        log.info("Caching all attack related info on startup");
        getAllAttackRelatedInfo();
    }

    public void getAllAttackRelatedInfo() {
        List<String> allAttackIds = attackRepository.findAllAttackIds();
        processInBatchesAsync(allAttackIds);
    }

    private void processInBatchesAsync(List<String> allAttackIds) {
        int start = 0;
        int batchSize = 50;
        // Attack Id를 batchSize개씩 묶어서 병렬로 처리
        while (start < allAttackIds.size()) {
            int end = Math.min(start + batchSize, allAttackIds.size());
            List<String> batch = new ArrayList<>(allAttackIds.subList(start, end));
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                fetchAndCacheAttackInfo(batch);
            }, taskExecutor);
            // 모든 Future가 완료될 때까지 기다림
            future.join();
            start += batchSize;
        }
    }

    public List<AttackRelatedResponseDto> fetchAndCacheAttackInfo(List<String> attackIds) {
        List<CompletableFuture<AttackRelatedResponseDto>> futures = attackIds.stream()
                .map(attackId -> CompletableFuture.supplyAsync(() -> {
                    // Attack Id 별로 Attack 관련 정보를 조회하고 캐싱
                    // 캐시된 데이터가 있으면 반환, 없으면 조회 후 캐싱
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

    // Attack Id 별로 Attack 관련 정보 조회
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
