package ai.greenmate.greenmate_backend.global.email;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MailTemplate {
  private String address;
  private String title;
  private String content;

}
