package com.spring.boot.service;

/**
 * Description:
 *
 * @JDK Version:1.8.0_40
 * Version: V1.0
 * Author: hs
 * Date: 2018/4/16 16:14
 */

public interface MailService {
    /**
     * 发送简单邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendSimpleMail(String to, String subject, String content);

    /**
     * 发送html格式邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content);

    /**
     * 发送带附件邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath);

    /**
     * 发送带静态资源邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String filePath, String rscId);
}
