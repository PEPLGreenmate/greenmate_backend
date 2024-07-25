package ai.greenmate.greenmate_backend.global.jwt;

import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.domain.member.repository.MemberRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.core.authority.mapping.NullAuthoritiesMapper;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final MemberRepository memberRepository;

  private GrantedAuthoritiesMapper authoritiesMapper = new NullAuthoritiesMapper();//5

  private final String NO_CHECK_URL = "/api/auth/login";//1

  /**
   * 1. 리프레시 토큰이 오는 경우 -> 유효하면 AccessToken 재발급후, 필터 진행 X, 바로 튕기기
   *
   * 2. 리프레시 토큰은 없고 AccessToken만 있는 경우 -> 유저정보 저장후 필터 계속 진행
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    //login의 경우 당연히 토큰이 없기 때문에 RefreshToken을 생각 안하고 진행.
    if(request.getRequestURI().equals(NO_CHECK_URL)) {
      filterChain.doFilter(request, response);
      return;//안해주면 아래로 내려가서 계속 필터를 진행하게됨
    }

    //JWT로 RefreshToken 가져오기
    String refreshToken = jwtService
            .extractRefreshToken(request)
            .filter(jwtService::isTokenValid)
            .orElse(null); //2

    //refreshToken이 있으면 체크
    if(refreshToken != null){
      checkRefreshTokenAndReIssueAccessToken(response, refreshToken);//3
      return;
    }

    //refreshToken이 없으면 생성 후 체크
    checkAccessTokenAndAuthentication(request, response, filterChain);//4
  }

  private void checkAccessTokenAndAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    jwtService.extractAccessToken(request).filter(jwtService::isTokenValid).ifPresent(

            accessToken -> jwtService.extractEmail(accessToken).ifPresent(

                    email -> memberRepository.findByEmail(email).ifPresent(

                            users -> saveAuthentication(users)
                    )
            )
    );

    filterChain.doFilter(request,response);
  }

  private void saveAuthentication(Member member) {
    UserDetailsImpl userDetails = new UserDetailsImpl(member);

    Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null,authoritiesMapper.mapAuthorities(userDetails.getAuthorities()));

    SecurityContext context = SecurityContextHolder.createEmptyContext();//5
    context.setAuthentication(authentication);
    SecurityContextHolder.setContext(context);
  }

  private void checkRefreshTokenAndReIssueAccessToken(HttpServletResponse response, String refreshToken) {
    memberRepository.findByRefreshToken(refreshToken)
            .ifPresent(
                    users -> jwtService.sendAccessToken(response, jwtService.createAccessToken(users.getEmail()))
    );
  }
}
