package kr.re.dslab.threatmodeling.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import kr.re.dslab.threatmodeling.type.dto.ControlResponseDto;
import kr.re.dslab.threatmodeling.type.entity.QControl;
import kr.re.dslab.threatmodeling.type.entity.QControlAttackMapping;

import java.util.List;

public class ControlRepositoryImpl implements ControlRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ControlRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<ControlResponseDto> findControlsByAttackId(String attackId) {
        QControl control = QControl.control;
        QControlAttackMapping controlAttackMapping = QControlAttackMapping.controlAttackMapping;

        return queryFactory
                .select(Projections.constructor(ControlResponseDto.class,
                        control.controlId,
                        control.controlName))
                .from(control)
                .join(controlAttackMapping).on(control.controlId.eq(controlAttackMapping.controlId))
                .where(controlAttackMapping.attackId.eq(attackId))
                .fetch();
    }

}