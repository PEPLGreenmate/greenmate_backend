package ai.greenmate.greenmate_backend.domain.member.entity;

import ai.greenmate.greenmate_backend.global.entity.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Member extends BaseEntity {

  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private long id;
  private String email;
  private String password;
  private String nickname;
  @Enumerated(value = EnumType.STRING)
  private Gender gender;
  private String address;
  private LocalDate birthday;
  private int feed;
  private int interaction;
  private int depressIndex;
  private int stressIndex;
  private int anxietyIndex;
  private LocalDateTime depressTestAt;
  private LocalDateTime stressTestAt;
  private LocalDateTime anxietyTestAt;
}
