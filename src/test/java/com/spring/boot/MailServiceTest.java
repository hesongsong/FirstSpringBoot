package com.spring.boot;

import com.spring.boot.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Description:
 *
 * @JDK Version:1.8.0_40
 * Version: V1.0
 * Author: hs
 * Date: 2018/4/16 16:21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    public void testSimpleMail() throws Exception {
        mailService.sendSimpleMail("hs246437@126.com","test simple mail"," hello this is simple mail");
    }

    @Test
    public void testHtmlMail() throws Exception {
        String content="<html>\n" +
                "<body>\n" +
                "    <h3>hello world ! 这是一封Html邮件!</h3>\n" +
                "</body>\n" +
                "</html>";
        mailService.sendHtmlMail("hs246437@126.com", "test simple mail", content);
    }

    @Test
    public void sendAttachmentsMail() {
        String filePath="C:\\Users\\hs246\\Desktop\\地震局.txt";
        mailService.sendAttachmentsMail("hs246437@126.com", "主题：带附件的邮件", "有附件，请查收！", filePath);
    }

    @Test
    public void sendInlineResourceMail() {
        String rscId = "neo006";
        String content="<html>\n" +
                "<body>\n" +
                "    这是有图片的邮件：<img src=\'cid:" + rscId + "\' >\n" +
                "</body>\n" +
                "</html>";
        String imgPath = "D:\\YJHH\\文档\\服务器配置.png";

        mailService.sendInlineResourceMail("hs246437@126.com", "主题：这是有图片的邮件", content, imgPath, rscId);
    }
}
