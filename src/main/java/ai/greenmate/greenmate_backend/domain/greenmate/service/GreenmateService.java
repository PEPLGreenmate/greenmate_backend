package ai.greenmate.greenmate_backend.domain.greenmate.service;

import ai.greenmate.greenmate_backend.domain.greenmate.dto.BondingResponse;
import ai.greenmate.greenmate_backend.domain.greenmate.dto.HomeInfoResponse;
import ai.greenmate.greenmate_backend.domain.greenmate.dto.PostGreenmatesRequest;
import ai.greenmate.greenmate_backend.domain.greenmate.dto.WateringResponse;
import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import ai.greenmate.greenmate_backend.domain.greenmate.repository.GreenmateInfoRepository;
import ai.greenmate.greenmate_backend.domain.greenmate.repository.GreenmateRepository;
import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.domain.member.repository.MemberRepository;
import ai.greenmate.greenmate_backend.global.dto.BaseResponseStatus;
import ai.greenmate.greenmate_backend.global.enums.ExperienceRatio;
import ai.greenmate.greenmate_backend.global.exception.GreenmateException;
import ai.greenmate.greenmate_backend.global.exception.exception.AuthException;
import ai.greenmate.greenmate_backend.global.jwt.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GreenmateService {

  private final MemberRepository memberRepository;
  private final GreenmateRepository greenmateRepository;
  private final GreenmateInfoRepository greenmateInfoRepository;
  private final JwtService jwtService;

  public long setGreenmates(PostGreenmatesRequest postGreenmatesRequest) {
    //TODO : 그린메이트 생성 로직 추가 필요
    return 1L;
  }

  public HomeInfoResponse findHomeInfo() {
    String email = jwtService.getEmail();
    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new AuthException(BaseResponseStatus.NOT_FOUND));
    Greenmate greenmate = greenmateRepository.findByMember(member)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));

    //TODO : GPT 생성 연결 필요.
    return HomeInfoResponse.of(greenmate, "안녕");
  }

  public WateringResponse watering() {
    final int INCREASE_ENERGY = 10;
    final int DECREASE_WATER = -1;
    String email = jwtService.getEmail();

    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new AuthException(BaseResponseStatus.NOT_FOUND));
    if (member.getWater() + DECREASE_WATER < 0) {
      throw new GreenmateException(BaseResponseStatus.NOT_ENOUGH_WATER);
    }

    Greenmate greenmate = greenmateRepository.findByMember(member)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));
    greenmate.updateEnergy(INCREASE_ENERGY);
    member.updateWater(DECREASE_WATER);
    return WateringResponse.builder()
            .water(member.getWater())
            .energy(greenmate.getEnergy())
            .build();
  }

  public BondingResponse bonding() {
    final int DECREASE_ENERGY = -10;
    final int DECREASE_BOND = -1;
    String email = jwtService.getEmail();

    Member member = memberRepository.findByEmail(email)
            .orElseThrow(() -> new AuthException(BaseResponseStatus.NOT_FOUND));
    if (member.getBond() + DECREASE_BOND < 0) {
      throw new GreenmateException(BaseResponseStatus.NOT_ENOUGH_BOND);
    }

    Greenmate greenmate = greenmateRepository.findByMember(member)
            .orElseThrow(() -> new GreenmateException(BaseResponseStatus.NOT_FOUND));

    if(greenmate.getEnergy() + DECREASE_ENERGY < 0) {
      throw new GreenmateException(BaseResponseStatus.NOT_ENOUGH_ENERGY);
    }
    greenmate.updateExperience(ExperienceRatio.getExperienceByEnergy(greenmate.getEnergy()));
    greenmate.updateEnergy(DECREASE_ENERGY);
    member.updateBond(DECREASE_BOND);
    return BondingResponse.builder()
            .bond(member.getBond())
            .energy(greenmate.getEnergy())
            .experience(greenmate.getExperience())
            .build();
  }
}
