package ai.greenmate.greenmate_backend.domain.greenmate.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostGreenmateRequest {
  private String name;
  private String greenmateType;
  private String expectation;

  @Override
  public String toString() {
    return "PostGreenmateRequest{" +
            "name='" + name + '\'' +
            ", greenmateType='" + greenmateType + '\'' +
            ", expectation='" + expectation + '\'' +
            '}';
  }
}
