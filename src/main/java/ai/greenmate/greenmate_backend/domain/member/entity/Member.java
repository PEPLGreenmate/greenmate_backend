package ai.greenmate.greenmate_backend.domain.member.entity;

import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert
@Entity
public class Member extends BaseEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String email;
  private String password;
  private String nickname;
  @Enumerated(value = EnumType.STRING)
  private Gender gender;
  private String address;
  private LocalDate birthday;

  @ColumnDefault("0")
  private int water;
  @ColumnDefault("0")
  private int bond;
  @ColumnDefault("0")
  private int depressIndex;
  @ColumnDefault("0")
  private int stressIndex;
  @ColumnDefault("0")
  private int anxietyIndex;
  private LocalDateTime depressTestAt;
  private LocalDateTime stressTestAt;
  private LocalDateTime anxietyTestAt;
  private String refreshToken;

  public void updateRefreshToken(String refreshToken) {
    this.refreshToken = refreshToken;
  }

  public void destroyRefreshToken() {
    this.refreshToken = null;
  }

  @Builder
  public Member(String email, String nickname, String password, Gender gender, LocalDate birthday, String address) {
    this.email = email;
    this.nickname = nickname;
    this.password = password;
    this.gender = gender;
    this.birthday = birthday;
    this.address = address;
  }

  public void updatePassword(String password) {
    this.password = password;
  }

  public void updateWater(int value) {
    this.water += value;
  }

  public void updateBond(int decreaseBond) {
    this.bond += decreaseBond;
  }
}
