package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class HomeInfoResponse {
  private int bond; //교감하기 개수
  private int water; //물 개수
  private List<HomeGreenmateInfoDTO> greenmates;


  @Builder
  public HomeInfoResponse(int bond, int water, List<HomeGreenmateInfoDTO> greenmates) {
    this.bond = bond;
    this.water = water;
    this.greenmates = greenmates;
  }

  public static HomeInfoResponse of(Member member, List<HomeGreenmateInfoDTO> greenmates) {
    return HomeInfoResponse.builder()
            .bond(member.getBond())
            .water(member.getWater())
            .greenmates(greenmates)
            .build();
  }
}
