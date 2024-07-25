package ai.greenmate.greenmate_backend.global.email;

import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {
  private final JavaMailSender emailSender;
  private final String SENDER = "no-reply@greenmate.ai";

  public void sendSimpleMessage(MailTemplate mailTemplate) {
    SimpleMailMessage message = new SimpleMailMessage();
    message.setFrom(SENDER);
    message.setTo(mailTemplate.getAddress());
    message.setSubject(mailTemplate.getTitle());
    message.setText(mailTemplate.getContent());
    emailSender.send(message);
  }
}
