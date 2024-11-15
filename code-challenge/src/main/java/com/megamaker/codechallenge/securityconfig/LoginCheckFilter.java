package com.megamaker.codechallenge.securityconfig;

import com.megamaker.codechallenge.domain.entity.User;
import com.megamaker.codechallenge.repository.UserRepository;
import com.megamaker.codechallenge.service.exception.UserNotFoundException;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.util.Base64;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class LoginCheckFilter extends OncePerRequestFilter {
    private final String[] permitPathArr;
    private final UserRepository userRepository;
    private final Environment environment;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");
        if (authorization == null || !authorization.startsWith("Bearer")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"message\": \"토큰 헤더 정보 불일치\"}");
            return;
        }
        String jwt = authorization.split(" ")[1];

        try {
            byte[] secretKeyBytes = Base64.getEncoder().encode(environment.getProperty("token.secret").getBytes());
            SecretKey secretKey = new SecretKeySpec(secretKeyBytes, Jwts.SIG.HS256.key().build().getAlgorithm());

            JwtParser jwtParser = Jwts.parser()
                    .verifyWith(secretKey)
                    .build();
            String userId = jwtParser.parseSignedClaims(jwt).getPayload().getSubject();

            // 회원 조회
            User foundUser = userRepository.findByProviderId(userId).orElseThrow(UserNotFoundException::new);

            Authentication auth = new UsernamePasswordAuthenticationToken(  // 새 유저 인증 객체 생성
                    foundUser.getProviderId(),
                    null,
                    List.of(new SimpleGrantedAuthority(foundUser.getRole().name()))
            );

            SecurityContextHolder.getContext().setAuthentication(auth);
            doFilter(request, response, filterChain);
        } catch (RuntimeException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.setContentType("application/json; charset=UTF-8");
            response.getWriter().write("{\"message\": \"토큰 파싱 에러\"}");
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        for (String path : permitPathArr) {
            if (request.getServletPath().equals(path)) {
                return true;
            }
        }
        return false;
    }
}
