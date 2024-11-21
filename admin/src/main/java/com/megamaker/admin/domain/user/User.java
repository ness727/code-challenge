package com.megamaker.admin.domain.user;

import com.megamaker.admin.domain.BaseTimeDate;
import com.megamaker.admin.domain.user.Role;
import com.megamaker.admin.domain.user.dto.RequestUserUpdate;
import com.megamaker.admin.securityconfig.Provider;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name = "users")
@Entity
public class User extends BaseTimeDate implements UserDetails {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.ORDINAL)
    private Provider provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "provider_nickname")
    private String providerNickname;

    private String nickname;

    @Column(name = "solve_count")
    private Integer solveCount;

    private Integer score;

    @Enumerated(EnumType.ORDINAL)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return providerNickname;
    }

    @Override
    public String getUsername() {
        return providerId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void update(RequestUserUpdate request) {
        this.nickname = request.getNickname();
        this.role = request.getRole();
    }
}

