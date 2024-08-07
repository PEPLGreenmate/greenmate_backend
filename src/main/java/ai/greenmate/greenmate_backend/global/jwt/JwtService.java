package ai.greenmate.greenmate_backend.global.jwt;

import ai.greenmate.greenmate_backend.domain.member.repository.MemberRepository;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


@Transactional
@Service
@RequiredArgsConstructor
@Setter(value = AccessLevel.PRIVATE)
@Slf4j
public class JwtService {
  //== jwt.yml에 설정된 값 가져오기 ==//
  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.access.expiration}")
  private long accessTokenValidityInSeconds;
  @Value("${jwt.refresh.expiration}")
  private long refreshTokenValidityInSeconds;
  @Value("${jwt.access.header}")
  private String accessHeader;
  @Value("${jwt.refresh.header}")
  private String refreshHeader;

  private static final String ACCESS_TOKEN_SUBJECT = "AccessToken";
  private static final String REFRESH_TOKEN_SUBJECT = "RefreshToken";
  private static final String USERNAME_CLAIM = "email";
  private static final String BEARER = "Bearer ";

  private final MemberRepository memberRepository;
  private final ObjectMapper objectMapper;

  /*
  Access-Token 생성 메소드
  이메일을 Claim으록 갖고있다.
   */
  public String createAccessToken(String email) {
    return JWT.create() //JWT 토큰을 생성하는 빌더를 반환합니다.

            .withSubject(ACCESS_TOKEN_SUBJECT)
            //빌더를 통해 JWT의 Subject를 정합니다. AccessToken이므로 위에서 설정했던
            //AccessToken의 subject를 합니다.

            .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenValidityInSeconds * 1000))
            //만료시간을 설정하는 것입니다. 현재 시간 + 저희가 설정한 시간(밀리초) * 1000을 하면
            //현재 accessTokenValidityInSeconds이 80이기 때문에
            //현재시간에 80 * 1000 밀리초를 더한 '현재시간 + 80초'가 설정이 되고
            //따라서 80초 이후에 이 토큰은 만료됩니다.

            .withClaim(USERNAME_CLAIM, email)
            //클레임으로는 email 하나만 사용합니다.
            //추가적으로 식별자나, 이름 등의 정보를 더 추가가능합니다.
            //추가하는 경우 .withClaim(클래임 이름, 클래임 값) 으로 설정합니다.

            .sign(Algorithm.HMAC512(secret));
    //HMAC512 알고리즘을 사용하여, 저희가 지정한 secret 키로 암호화 합니다.
  }

  /*
  Refresh-Token 생성 메서드
   */
  public String createRefreshToken() {
    return JWT.create()
            .withSubject(REFRESH_TOKEN_SUBJECT)
            .withExpiresAt(new Date(System.currentTimeMillis() + refreshTokenValidityInSeconds * 1000))
            .sign(Algorithm.HMAC512(secret));
  }

  /*
  Refresh-Token 업데이트 메서드
   */
  public void updateRefreshToken(String email, String refreshToken) {
    memberRepository.findByEmail(email)
            .ifPresentOrElse(
                    users -> users.updateRefreshToken(refreshToken),
                    () -> new Exception("회원 조회 실패")
            );
  }

  /*
  Refresh-Token 제거 메서드
   */
  public void destroyRefreshToken(String email) {
    memberRepository.findByEmail(email)
            .ifPresentOrElse(
                    users -> users.destroyRefreshToken(),
                    () -> new Exception("회원 조회 실패")
            );
  }
  /*
  Access, Refresh 토큰 전송.
  헤더에 Authroization,
  */
  public void sendAccessAndRefreshToken(HttpServletResponse response, String accessToken, String refreshToken) {
    response.setStatus(HttpServletResponse.SC_OK);

    setAccessTokenHeader(response, accessToken);
    setRefreshTokenHeader(response, refreshToken);

    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put(ACCESS_TOKEN_SUBJECT, accessToken);
    tokenMap.put(REFRESH_TOKEN_SUBJECT, refreshToken);

  }

  public void sendAccessToken(HttpServletResponse response, String accessToken) {
    response.setStatus(HttpServletResponse.SC_OK);

    setAccessTokenHeader(response, accessToken);

    Map<String, String> tokenMap = new HashMap<>();
    tokenMap.put(ACCESS_TOKEN_SUBJECT, accessToken);
  }

  public Optional<String> extractAccessToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(accessHeader)).filter(
            accessToken -> accessToken.startsWith(BEARER)
    ).map(accessToken -> accessToken.replace(BEARER, ""));
  }

  public Optional<String> extractRefreshToken(HttpServletRequest request) {
    return Optional.ofNullable(request.getHeader(refreshHeader)).filter(
            refreshToken -> refreshToken.startsWith(BEARER)
    ).map(refreshToken -> refreshToken.replace(BEARER, ""));
  }

  public Optional<String> extractEmail(String accessToken) {
    try {
      return Optional.ofNullable(
              JWT.require(Algorithm.HMAC512(secret)).build().verify(accessToken).getClaim(USERNAME_CLAIM)
                      .asString());
    } catch (Exception e) {
      log.error(e.getMessage());
      return Optional.empty();
    }
  }

  public void setAccessTokenHeader(HttpServletResponse response, String accessToken) {
    response.setHeader(accessHeader, accessToken);
  }

  public void setRefreshTokenHeader(HttpServletResponse response, String refreshToken) {
    response.setHeader(refreshHeader, refreshToken);
  }

  public boolean isTokenValid(String token) {
    try {
      Jwts.parserBuilder()
              .setSigningKey(secret.getBytes())
              .build()
              .parseClaimsJws(token);
      return true;
    } catch (SecurityException | MalformedJwtException e) {
      log.info("Invalid JWT Token", e);
    } catch (ExpiredJwtException e) {
      log.info("Expired JWT Token", e);
    } catch (UnsupportedJwtException e) {
      log.info("Unsupported JWT Token", e);
    } catch (IllegalArgumentException e) {
      log.info("JWT claims string is empty.", e);
    }
    return false;
  }

  public String getEmail(){
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    return authentication.getName();
  }
}

