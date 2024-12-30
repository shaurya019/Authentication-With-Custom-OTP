package Auth.OtpVerify.service.impl;

import Auth.OtpVerify.dto.EmailDetails;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @org.springframework.beans.factory.annotation.Value("${spring.mail.username}")
    private String emailSender;


    public void sendEmail(EmailDetails emailDetails){
        try{
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(emailSender);
            mailMessage.setTo(emailDetails.getRecipient());
            mailMessage.setText(emailDetails.getMessageBody());
            mailMessage.setSubject(emailDetails.getSubject());

            javaMailSender.send(mailMessage);
            log.info("Message sent to: {}", emailDetails.getRecipient());
            log.info("Message sender: {}", emailSender);

        }catch (MailException e) {
            throw new RuntimeException(e);
        }
    }
}
