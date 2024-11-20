package com.megamaker.admin.repository;

import com.megamaker.admin.domain.Level;
import com.megamaker.admin.domain.Role;
import com.megamaker.admin.dto.problem.ProblemSearchCond;
import com.megamaker.admin.dto.user.UserSearchCond;
import com.megamaker.admin.entity.Problem;
import com.megamaker.admin.entity.User;
import com.megamaker.admin.securityconfig.Provider;
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

import static com.megamaker.admin.entity.QProblem.problem;
import static com.megamaker.admin.entity.QUser.user;

@Repository
public class UserRepositoryImpl implements UserRepository {
    private final EntityManager entityManager;
    private final JPAQueryFactory queryFactory;

    public UserRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<User> findByProviderIdAndRole(String providerId, Role role) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if (StringUtils.hasText(providerId)) {
            booleanBuilder.and(user.providerId.eq(providerId));
        }
        if (role != null) {
            booleanBuilder.and(user.role.eq(role));
        }

        User foundUser = queryFactory.select(user)
                .from(user)
                .where(booleanBuilder)
                .fetchFirst();

        return Optional.ofNullable(foundUser);
    }

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    // -------- 전체 조회 ---------
    
    @Override
    public Page<User> findAll(UserSearchCond userSearchCond, Pageable pageable) {
        BooleanBuilder builder = getCondResult(userSearchCond);

        // 전체 페이지 수 구하기
        int totalPageSize = queryFactory.select(user)
                .from(user)
                .where(builder)
                .fetch().size();

        List<User> foundUserList = queryFactory.select(user)
                .from(user)
                .where(builder)
                .orderBy(getOrderSpecifiers(pageable))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(foundUserList, pageable, totalPageSize);
    }

    // 검색 로직
    private static BooleanBuilder getCondResult(UserSearchCond userSearchCond) {
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        String nickname = userSearchCond.getSearch();

        // 닉네임으로 검색
        if (StringUtils.hasText(nickname)) {
            booleanBuilder.or(user.nickname.contains(nickname));  // 해당 글자 포함으로 검색
            booleanBuilder.or(user.providerNickname.contains(nickname));
        }

        return booleanBuilder;
    }

    // 정렬 로직
    private static OrderSpecifier[] getOrderSpecifiers(Pageable pageable) {
        return pageable.getSort().stream()
                .map((Sort.Order order) -> {
                    Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                    Expression<?> expression = switch (order.getProperty()) {
                        case "id" -> user.id;
                        case "nickname" -> user.nickname;
                        case "score" -> user.score;
                        case "solvedCount" -> user.solveCount;
                        default -> null;
                    };
                    return new OrderSpecifier(direction, expression);
                }).toArray(OrderSpecifier[]::new);
    }

    // -------- 단건 조회 ---------
    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(
                queryFactory.selectFrom(user)
                        .where(user.id.eq(id))
                        .fetchFirst()
        );
    }

//    // -------- 저장 ---------
//    @Override
//    public void save(Problem problem) {
//        entityManager.persist(problem);
//    }
//
//    // -------- 삭제 ---------
//    @Override
//    public void remove(Long id) {
//        Problem foundProblem = entityManager.getReference(Problem.class, id);
//        entityManager.remove(foundProblem);
//    }
}
