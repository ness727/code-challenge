package com.megamaker.codechallenge.repository;

import com.megamaker.codechallenge.domain.Level;
import com.megamaker.codechallenge.dto.ProblemSearchCond;
import com.megamaker.codechallenge.entity.Problem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.megamaker.codechallenge.entity.QProblem.problem;

@Transactional
@Repository
public class ProblemRepositoryImpl implements ProblemRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public ProblemRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public List<Problem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable) {
        BooleanBuilder builder = getCondResult(problemSearchCond);

        return queryFactory.select(problem)
                .from(problem)
                .where(builder)
                .orderBy(getOrderSpecifiers(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private static BooleanBuilder getCondResult(ProblemSearchCond problemSearchCond) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String title = problemSearchCond.getTitle();
        Level level = Level.intToEnum(problemSearchCond.getLevel());

        // 제목으로 검색
        if (StringUtils.hasText(title)) {
            booleanBuilder.and(problem.title.contains(title));  // 해당 글자 포함으로 검색
        }
        // 레벨로 검색
        if (level != null) {
            booleanBuilder.and(problem.level.eq(level));  // 해당 레벨과 동일한 결과 검색
        }
        return booleanBuilder;
    }

    private static OrderSpecifier[] getOrderSpecifiers(Pageable pageable) {
        return pageable.getSort().stream()
                .map((Sort.Order order) -> {
                    Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                    Expression<?> expression = switch (order.getProperty()) {
                        case "title" -> problem.title;
                        case "level" -> problem.level;
                        default -> null;
                    };
                    return new OrderSpecifier(direction, expression);
                }).toArray(OrderSpecifier[]::new);
    }
}
