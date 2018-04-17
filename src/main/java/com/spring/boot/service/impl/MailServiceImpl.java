package com.spring.boot.service.impl;

import com.spring.boot.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Description:
 *
 * @JDK Version:1.8.0_40
 * Version: V1.0
 * Author: hs
 * Date: 2018/4/16 16:14
 */
@Service
public class MailServiceImpl implements MailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.fromMail.addr}")
    private String from;

    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);

        try {
            mailSender.send(message);
            logger.info("简单邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送简单邮件时发生异常！", e);
        }

    }

    /**
     * 发送html格式邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(mailMessage);
            logger.info("html邮件发送成功");
        } catch (MessagingException e) {
            logger.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * 发送带附件邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendAttachmentsMail(String to, String subject, String content, String filePath) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);
            //添加多个附件可以使用多条 helper.addAttachment(fileName, file)

            mailSender.send(mimeMessage);
            logger.info("带附件的邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送带附件的邮件时发生异常！", e);
        }
    }

    /**
     * 发送带静态资源邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendInlineResourceMail(String to, String subject, String content, String filePath, String rscId) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();

        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            helper.addInline(rscId, file);

            mailSender.send(mimeMessage);
            logger.info("嵌入静态资源的邮件已经发送。");
        } catch (Exception e) {
            logger.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }


}
