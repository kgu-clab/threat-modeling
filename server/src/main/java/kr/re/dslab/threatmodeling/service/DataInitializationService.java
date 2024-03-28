package kr.re.dslab.threatmodeling.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import kr.re.dslab.threatmodeling.exception.NotFoundException;
import kr.re.dslab.threatmodeling.repository.AttackRepository;
import kr.re.dslab.threatmodeling.repository.ControlAttackMappingRepository;
import kr.re.dslab.threatmodeling.repository.ControlRepository;
import kr.re.dslab.threatmodeling.repository.CveRepository;
import kr.re.dslab.threatmodeling.repository.DefendRepository;
import kr.re.dslab.threatmodeling.repository.MitigationAttackMappingRepository;
import kr.re.dslab.threatmodeling.repository.MitigationRepository;
import kr.re.dslab.threatmodeling.type.dto.json.ControlAttackAttackDto;
import kr.re.dslab.threatmodeling.type.dto.json.ControlAttackDto;
import kr.re.dslab.threatmodeling.type.dto.json.MitigationAttackDto;
import kr.re.dslab.threatmodeling.type.dto.json.MitigationDefendDto;
import kr.re.dslab.threatmodeling.type.entity.Attack;
import kr.re.dslab.threatmodeling.type.entity.Control;
import kr.re.dslab.threatmodeling.type.entity.ControlAttackMapping;
import kr.re.dslab.threatmodeling.type.entity.Cve;
import kr.re.dslab.threatmodeling.type.entity.Defend;
import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import kr.re.dslab.threatmodeling.type.entity.MitigationAttackMapping;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DataInitializationService {

    private final MitigationRepository mitigationRepository;

    private final AttackRepository attackRepository;

    private final MitigationAttackMappingRepository mitigationAttackMappingRepository;

    private final ControlRepository controlRepository;

    private final ControlAttackMappingRepository controlAttackMappingRepository;

    private final DefendRepository defendRepository;

    private final CveRepository cveRepository;

    private final ResourceLoader resourceLoader;

    private final ObjectMapper objectMapper;

    @PostConstruct
    public void initDatabase() {
        try {
            log.info("Initializing database");
            long start = System.currentTimeMillis();
            // Mitigation과 Attack에 대한 정보를 JSON 파일에서 읽어와 저장
            initializeMitigationsAndAttacks();
            // Control과 Attack에 대한 정보를 JSON 파일에서 읽어와 저장
            initializeControlsAndControlAttackMappings();
            // Mitigation과 Defend에 대한 정보를 JSON 파일에서 읽어와 저장
            initializeMitigationsAndDefends();
            // CVE에 대한 정보를 JSON 파일에서 읽어와 저장
            initializeCves();
            long end = System.currentTimeMillis();
            log.info("Database initialized successfully");
            log.info("Database initialization took {} ms", end - start);
        } catch (IOException e) {
            log.error("Failed to initialize database", e);
        }
    }

    // JSON 파일을 읽어서 객체로 변환
    private <T> List<T> loadEntitiesFromJson(String filePath, TypeReference<List<T>> typeReference) throws IOException {
        Resource resource = resourceLoader.getResource(filePath);
        return objectMapper.readValue(resource.getInputStream(), typeReference);
    }

    private void initializeMitigationsAndAttacks() throws IOException {
        List<MitigationAttackDto> mitigations = loadEntitiesFromJson("classpath:mitigationAttackRelation.json", new TypeReference<>() {});
        mitigations.forEach(this::saveMitigationAndAttacks);
    }

    private void saveMitigationAndAttacks(MitigationAttackDto mitigationAttackDto) {
        Mitigation mitigation = Mitigation.of(mitigationAttackDto);
        mitigationRepository.save(mitigation);
        List<Attack> attacks = mitigationAttackDto.getRelatedAttackTechniques().stream()
                .map(Attack::of)
                .toList();
        attackRepository.saveAll(attacks);
        List<MitigationAttackMapping> mitigationAttackMappings = attacks.stream()
                .map(attack -> MitigationAttackMapping.builder()
                        .mitigationId(mitigation.getMitigationId())
                        .attackId(attack.getAttackId())
                        .build())
                .toList();
        mitigationAttackMappingRepository.saveAll(mitigationAttackMappings);
    }

    public void initializeControlsAndControlAttackMappings() throws IOException {
        List<ControlAttackDto> controlAttackDtos = loadEntitiesFromJson("classpath:controlAttackRelation.json", new TypeReference<>() {});
        controlAttackDtos
                .forEach(controlAttackDto -> {
                            Control control = controlRepository.save(controlAttackDto.getControl());
                            List<ControlAttackAttackDto> controlAttackAttackDtos = controlAttackDto.getRelatedAttackTechniques();
                            List<ControlAttackMapping> controlAttackMappings = controlAttackAttackDtos.stream()
                                    .map(controlAttackAttackDto -> ControlAttackMapping.builder()
                                            .controlId(control.getControlId())
                                            .attackId(controlAttackAttackDto.getAttackId())
                                            .build())
                                    .toList();
                            controlAttackMappingRepository.saveAll(controlAttackMappings);
                        }
                );
    }

    private void initializeMitigationsAndDefends() throws IOException {
        List<MitigationDefendDto> mitigationDefendDtos = loadEntitiesFromJson("classpath:mitigationDefendRelation.json", new TypeReference<>() {});
        List<Mitigation> mitigations = mitigationDefendDtos.stream().map(this::mapToMitigation).toList();
        mitigationRepository.saveAll(mitigations);
    }

    private Mitigation mapToMitigation(MitigationDefendDto mitigationDefendDto) {
        Mitigation mitigation = mitigationRepository.findById(mitigationDefendDto.getMitigationId())
                .orElseThrow(() -> new NotFoundException("Mitigation not found"));
        List<Defend> defends = mitigationDefendDto.getRelatedDefendTechniques().stream()
                .map(defendDto -> Defend.of(defendDto, mitigation))
                .toList();
        defendRepository.saveAll(defends);
        mitigation.setRelatedDefendTechniques(defends);
        return mitigation;
    }

    private void initializeCves() throws IOException {
        List<Cve> cves = loadEntitiesFromJson("classpath:attackToCveMappings.json", new TypeReference<>() {});
        cveRepository.saveAll(cves);
    }

}
