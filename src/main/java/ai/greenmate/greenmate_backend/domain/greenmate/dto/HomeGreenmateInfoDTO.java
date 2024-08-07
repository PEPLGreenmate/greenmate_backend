package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.Greenmate;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HomeGreenmateInfoDTO {
  private String type; // 그린메이트 타입
  private String greenmateName; // 그린메이트 이름
  private int experience; // 그린메이트 경험치
  private int level; // 그린메이트 레벨
  private int energy; // 그린메이트 에너지
  private long greenmateId;

  @Builder
  public HomeGreenmateInfoDTO(String type, String greenmateName, int experience, int level, int energy, long greenmateId) {
    this.type = type;
    this.greenmateName = greenmateName;
    this.experience = experience;
    this.level = level;
    this.energy = energy;
    this.greenmateId = greenmateId;
  }

  public static HomeGreenmateInfoDTO fromEntity(Greenmate greenmate) {
    return HomeGreenmateInfoDTO.builder()
            .type(greenmate.getGreenmateTypeToLowerCase())
            .greenmateName(greenmate.getName())
            .experience(greenmate.getExperience())
            .level(greenmate.getLevel())
            .energy(greenmate.getEnergy())
            .greenmateId(greenmate.getId())
            .build();
  }
}
