package com.megamaker.codechallenge.problem.infra;

import com.megamaker.codechallenge.problem.domain.Problem;
import com.megamaker.codechallenge.problem.domain.ProblemRepository;
import com.megamaker.codechallenge.problem.domain.dto.ProblemSearchCond;
import com.megamaker.codechallenge.problem.domain.vo.Level;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.megamaker.codechallenge.problem.infra.QProblemEntity.problemEntity;


@Repository
public class ProblemRepositoryImpl implements ProblemRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public ProblemRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // -------- 전체 조회 ---------

    @Override
    public Page<Problem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable) {
        BooleanBuilder builder = getCondResult(problemSearchCond);

        int totalSize = queryFactory
                .selectFrom(problemEntity)
                .where(builder)
                .fetch().size();

        List<ProblemEntity> foundProblemListEntity = queryFactory
                .selectFrom(problemEntity)
                .where(builder)
                .orderBy(getOrderSpecifiers(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
        return new PageImpl<>(
                foundProblemListEntity.stream()
                        .map(ProblemEntity::toModel)
                        .toList(),
                pageable, totalSize);
    }

    // 검색 로직
    private static BooleanBuilder getCondResult(ProblemSearchCond problemSearchCond) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String title = problemSearchCond.getTitle();
        Level level = Level.intToEnum(problemSearchCond.getLevel());

        // 제목으로 검색
        if (StringUtils.hasText(title)) {
            booleanBuilder.and(problemEntity.title.contains(title));  // 해당 글자 포함으로 검색
        }
        // 레벨로 검색
        if (level != null) {
            booleanBuilder.and(problemEntity.level.eq(level));  // 해당 레벨과 동일한 결과 검색
        }
        return booleanBuilder;
    }

    // 정렬 로직
    private static OrderSpecifier[] getOrderSpecifiers(Pageable pageable) {
        return pageable.getSort().stream()
                .map((Sort.Order order) -> {
                    Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                    Expression<?> expression = switch (order.getProperty()) {
                        case "title" -> problemEntity.title;
                        case "level" -> problemEntity.level;
                        case "correctRate" -> problemEntity.correctRate;
                        default -> null;
                    };
                    return new OrderSpecifier(direction, expression);
                }).toArray(OrderSpecifier[]::new);
    }

    // -------- 단건 조회 ---------
    
    @Override
    public Optional<Problem> findById(Long id) {
        ProblemEntity foundProblemEntity = queryFactory.selectFrom(problemEntity)
                .where(problemEntity.id.eq(id))
                .fetchFirst();
        return Optional.ofNullable(foundProblemEntity).map(ProblemEntity::toModel);
    }

//    @Override
//    public List<Testcase> findTestcaseListById(Long id) {
//        return Objects.requireNonNull(queryFactory.select(problemEntity)
//                        .from(problemEntity)
//                        .innerJoin(problemEntity.testcaseEntityList, QTestcaseEntity.testcaseEntity).fetchJoin()
//                        .where(problemEntity.id.eq(id))
//                        .fetchFirst())
//                .getTestcaseEntityList().stream()
//                .map(TestcaseEntity::toModel)
//                .toList();
//    }

    @Override
    public void save(Problem problem) {
        ProblemEntity foundProblemEntity = entityManager.find(ProblemEntity.class, problem.getId());
        foundProblemEntity.update(problem);
//        entityManager.persist(ProblemEntity.from(problem));
    }
}
