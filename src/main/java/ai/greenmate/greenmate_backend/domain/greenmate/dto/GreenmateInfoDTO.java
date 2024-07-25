package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import ai.greenmate.greenmate_backend.domain.greenmate.entity.GreenmateInfo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class GreenmateInfoDTO {
  private String name;
  private String description;
  private String imageUrl;

  @Builder
  private GreenmateInfoDTO(String description, String imageUrl, String name) {
    this.description = description;
    this.imageUrl = imageUrl;
    this.name = name;
  }

  public static GreenmateInfoDTO of(GreenmateInfo greenmateInfo){
    return GreenmateInfoDTO.builder()
            .description(greenmateInfo.getDescription())
            .name(greenmateInfo.getName())
            .imageUrl(greenmateInfo.getImageUrl())
            .build();
  }
}
