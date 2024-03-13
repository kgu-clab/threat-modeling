package kr.re.dslab.threatmodeling.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.re.dslab.threatmodeling.type.dto.MitigationResponseDto;
import kr.re.dslab.threatmodeling.type.entity.Mitigation;
import kr.re.dslab.threatmodeling.type.entity.QMitigation;
import kr.re.dslab.threatmodeling.type.entity.QMitigationAttackMapping;

import java.util.List;

public class MitigationRepositoryImpl implements MitigationRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MitigationRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<MitigationResponseDto> findMitigationsByAttackId(String attackId) {
        QMitigation mitigation = QMitigation.mitigation;
        QMitigationAttackMapping mapping = QMitigationAttackMapping.mitigationAttackMapping;

        List<Mitigation> mitigations = queryFactory.selectFrom(mitigation)
                .innerJoin(mapping)
                .on(mitigation.mitigationId.eq(mapping.mitigationId))
                .where(mapping.attackId.eq(attackId))
                .fetch();

        return mitigations.stream()
                .map(m -> MitigationResponseDto.builder()
                        .mitigationId(m.getMitigationId())
                        .mitigationName(m.getMitigationName())
                        .mitigationUrl(m.getMitigationUrl())
                        .build()
                )
                .toList();
    }

}
