package ai.greenmate.greenmate_backend.global.config;

import ai.greenmate.greenmate_backend.global.email.MailProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
@RequiredArgsConstructor
public class MailConfig {

  private final MailProperties mailProperties;

  @Bean
  public JavaMailSender javaMailService() {
    Properties properties = new Properties();
    JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
    javaMailSender.setHost(this.mailProperties.getHost());
    javaMailSender.setUsername(this.mailProperties.getUsername());
    javaMailSender.setPassword(this.mailProperties.getPassword());
    javaMailSender.setPort(this.mailProperties.getPort());

    properties.put("mail.smtp.socketFactory.port", this.mailProperties.getPort());
    properties.put("mail.smtp.auth", true);
    properties.put("mail.smtp.starttls.enable", false);
    properties.put("mail.smtp.starttls.required", false);
    properties.put("mail.smtp.ssl.trust", this.mailProperties.getHost());
    properties.put("mail.smtp.socketFactory.fallback", false);

    javaMailSender.setJavaMailProperties(properties);
    javaMailSender.setDefaultEncoding("UTF-8");

    return javaMailSender;
  }
}

