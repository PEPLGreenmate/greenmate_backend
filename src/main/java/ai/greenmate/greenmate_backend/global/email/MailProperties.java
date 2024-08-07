package ai.greenmate.greenmate_backend.global.email;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.mail")
public class MailProperties {

  // SMTP 서버
  private String host;

  // 계정
  private String username;

  // 비밀번호
  private String password;

  // 포트번호
  private int port;
}
