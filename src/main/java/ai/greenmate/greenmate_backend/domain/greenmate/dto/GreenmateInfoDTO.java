package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.GreenmateInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GreenmateInfoDTO {
  private String type;
  private String description;

  @Builder
  public GreenmateInfoDTO(String type, String description) {
    this.type = type;
    this.description = description;
  }

  public static GreenmateInfoDTO of(GreenmateInfo greenmateInfo){
    return GreenmateInfoDTO.builder()
            .description(greenmateInfo.getDescription())
            .type(greenmateInfo.getGreenmateType().name().toLowerCase())
            .build();
  }
}
