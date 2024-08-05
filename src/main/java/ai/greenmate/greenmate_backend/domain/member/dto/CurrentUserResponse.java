package ai.greenmate.greenmate_backend.domain.member.dto;

import ai.greenmate.greenmate_backend.domain.member.entity.Member;
import ai.greenmate.greenmate_backend.global.util.DateUtils;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CurrentUserResponse {
  private String email;
  private String nickname;
  private String gender;
  private String birthday;
  private String address;
  private long greenmateCount;

  @Builder
  public CurrentUserResponse(String email, String nickname, String gender, String birthday, String address, long greenmateCount) {
    this.email = email;
    this.nickname = nickname;
    this.gender = gender;
    this.birthday = birthday;
    this.address = address;
    this.greenmateCount = greenmateCount;
  }

  public static CurrentUserResponse from(Member member, long greenmateCount) {
    return CurrentUserResponse.builder()
            .email(member.getEmail())
            .nickname(member.getNickname())
            .gender(member.getGenderToLowerCase())
            .birthday(DateUtils.getDateFromLocalDate(member.getBirthday()))
            .address(member.getAddress())
            .greenmateCount(greenmateCount)
            .build();
  }
}
