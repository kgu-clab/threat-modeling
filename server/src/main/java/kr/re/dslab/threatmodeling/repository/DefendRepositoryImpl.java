package kr.re.dslab.threatmodeling.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.re.dslab.threatmodeling.type.dto.DefendResponseDto;
import kr.re.dslab.threatmodeling.type.entity.QDefend;

import java.util.List;

public class DefendRepositoryImpl implements DefendRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public DefendRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<DefendResponseDto> findDefendsByMitigationId(String mitigationId) {
        QDefend defend = QDefend.defend;

        return queryFactory.select(Projections.constructor(DefendResponseDto.class,
                        defend.defendId,
                        defend.defendName,
                        defend.defendUrl)
                )
                .from(defend)
                .where(defend.mitigation.mitigationId.eq(mitigationId))
                .fetch();
    }

}
