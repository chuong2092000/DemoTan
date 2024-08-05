package com.example.demo.service.impl;

import com.example.demo.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;

@Service
@Transactional
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender javaMailSender;

    @Override
    public boolean send(String to, String email, String subject) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setTo(to);
            helper.setText(email, true);
            helper.setSubject(subject);
            helper.setFrom("WebTMDT@gmail.com", "WebTMDT.com");

            javaMailSender.send(message);
            return true;
        } catch (UnsupportedEncodingException | MessagingException e ) {
            return false;
        }
    }

    @Override
    public String buildEmail(String name, String link, String password, boolean isValidEmail) {
        String send = "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account </p>";
        String title = "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>";
        if (isValidEmail) {
            send = "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Password: " + password + "</p>";
            title = "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your password</span>";
        }
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">" +
                "" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>" +
                "" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" +
                "    <tbody><tr>" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">" +
                "        " +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">" +
                "          <tbody><tr>" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">" +
                "                  <tbody><tr>" +
                "                    <td style=\"padding-left:10px\">" +
                "                  " +
                "                    </td>" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">" +
                title +
                "                    </td>" +
                "                  </tr>" +
                "                </tbody></table>" +
                "              </a>" +
                "            </td>" +
                "          </tr>" +
                "        </tbody></table>" +
                "        " +
                "      </td>" +
                "    </tr>" +
                "  </tbody></table>" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">" +
                "    <tbody><tr>" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>" +
                "      <td>" +
                "        " +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">" +
                "                  <tbody><tr>" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>" +
                "                  </tr>" +
                "                </tbody></table>" +
                "        " +
                "      </td>" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>" +
                "    </tr>" +
                "  </tbody></table>" +
                "" +
                "" +
                "" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">" +
                "    <tbody><tr>" +
                "      <td height=\"30\"><br></td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td width=\"10\" valign=\"middle\"><br></td>" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">" +
                "        " +
                send +
                "        " +
                "      </td>" +
                "      <td width=\"10\" valign=\"middle\"><br></td>" +
                "    </tr>" +
                "    <tr>" +
                "      <td height=\"30\"><br></td>" +
                "    </tr>" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">" +
                "" +
                "</div></div>";
    }
}