package ai.greenmate.greenmate_backend.global.jwt;

import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Getter
@Component
@NoArgsConstructor
public class UserDetailsImpl implements UserDetails {
  private Member member;

  public UserDetailsImpl(Member member) {
    this.member = member;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of();
  }

  @Override
  public String getPassword() {
    return "";
  }

  @Override
  public String getUsername() {
    return member.getEmail();
  }
}
