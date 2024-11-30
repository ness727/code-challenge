package com.megamaker.admin.infra.jpa;

import com.megamaker.admin.domain.problem.vo.Level;
import com.megamaker.admin.domain.problem.ProblemRepository;
import com.megamaker.admin.domain.problem.dto.ProblemSearchCond;
import com.megamaker.admin.domain.problem.Problem;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

import static com.megamaker.admin.domain.problem.QProblem.problem;


@RequiredArgsConstructor
@Repository
public class ProblemRepositoryImpl implements ProblemRepository {
    @PersistenceContext
    private final EntityManager entityManager;
    private JPAQueryFactory queryFactory;

    @PostConstruct
    public void init() {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    // -------- 전체 조회 ---------
    
    @Override
    public Page<Problem> findAll(ProblemSearchCond problemSearchCond, Pageable pageable) {
        BooleanBuilder builder = getCondResult(problemSearchCond);

        // 전체 페이지 수 구하기
        int totalPageSize = queryFactory.select(problem)
                .from(problem)
                .where(builder)
                .fetch().size();

        List<Problem> foundProblemList = queryFactory.select(problem)
                .from(problem)
                .where(builder)
                .orderBy(getOrderSpecifiers(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(foundProblemList, pageable, totalPageSize);
    }

    // 검색 로직
    private static BooleanBuilder getCondResult(ProblemSearchCond problemSearchCond) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String title = problemSearchCond.getSearch();
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

    // 정렬 로직
    private static OrderSpecifier[] getOrderSpecifiers(Pageable pageable) {
        return pageable.getSort().stream()
                .map((Sort.Order order) -> {
                    Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                    Expression<?> expression = switch (order.getProperty()) {
                        case "id" -> problem.id;
                        case "title" -> problem.title;
                        case "level" -> problem.level;
                        case "correctRate" -> problem.correctRate;
                        default -> null;
                    };
                    return new OrderSpecifier(direction, expression);
                }).toArray(OrderSpecifier[]::new);
    }

    // -------- 단건 조회 ---------
    
    @Override
    public Optional<Problem> findById(Long id) {
        return Optional.ofNullable(
                queryFactory.selectFrom(problem)
                        //.leftJoin(problem.problemPictureList).fetchJoin()
                        //.leftJoin(problem.testcaseList).fetchJoin()
                        .where(problem.id.eq(id))
                        .fetchFirst()
        );
    }

    // -------- 저장 ---------
    @Override
    public void save(Problem problem) {
        entityManager.persist(problem);
    }

    // -------- 삭제 ---------
    @Override
    public void removeById(Long id) {
        Problem foundProblem = entityManager.getReference(Problem.class, id);
        entityManager.remove(foundProblem);
    }
}
