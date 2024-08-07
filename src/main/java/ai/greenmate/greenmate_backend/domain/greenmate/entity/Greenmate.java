package ai.greenmate.greenmate_backend.domain.greenmate.entity;

import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Greenmate extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String name;
  @ColumnDefault("100")
  private int energy;
  @ColumnDefault("0")
  private int experience;
  @ColumnDefault("0")
  private int level;
  private String expectation;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "greenmate_info_id")
  private GreenmateInfo greenmateInfo;

  @Builder
  public Greenmate(String name, String expectation, Member member, GreenmateInfo greenmateInfo) {
    this.name = name;
    this.expectation = expectation;
    this.member = member;
    this.greenmateInfo = greenmateInfo;
  }

  public void updateEnergy(int value) {
    this.energy += value;
  }

  public void updateExperience(int value) {
    this.experience += value;
  }

  public String getGreenmateTypeToLowerCase() {
    return this.greenmateInfo.getGreenmateType().name().toLowerCase();
  }
}
