package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class HomeInfoResponse {
  private String speech; //말풍선
  private String greenmateName; //이름
  private String imageUrl; //그린메이트 이미지 경로
  private int level; //레벨
  private int experience; //경험치
  private int energy; //에너지
  private int bond; //교감하기 개수
  private int water; //물 개수

  @Builder
  public HomeInfoResponse(int bond, int energy, int experience, String imageUrl, int level, String greenmateName, String speech, int water) {
    this.bond = bond;
    this.energy = energy;
    this.experience = experience;
    this.imageUrl = imageUrl;
    this.level = level;
    this.greenmateName = greenmateName;
    this.speech = speech;
    this.water = water;
  }

  public static HomeInfoResponse of(Greenmate greenmate, String speech) {
    return HomeInfoResponse.builder()
            .speech(speech)
            .greenmateName(greenmate.getName())
            .imageUrl(greenmate.getGreenmateInfo().getImageUrl())
            .experience(greenmate.getExperience())
            .energy(greenmate.getEnergy())
            .bond(greenmate.getMember().getBond())
            .water(greenmate.getMember().getWater())
            .level(greenmate.getLevel())
            .build();
  }
}
