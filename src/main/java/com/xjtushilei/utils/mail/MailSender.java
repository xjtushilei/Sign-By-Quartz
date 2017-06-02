package com.xjtushilei.utils.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * 邮件发送实现类
 *
 * @date 2014年4月26日 上午10:16:34
 * @project mailUtil
 */
public class MailSender {
    private MimeMessage mimeMsg; // MIME邮件对象
    private Session session; // 邮件会话对象
    private Properties props; // 系统属性
    private Multipart mp; // Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成
    private String username;// 发件人的用户名
    private String password;// 发件人的密码
    private String nickname;// 发件人的昵称
    private static Logger log = LoggerFactory.getLogger(MailSender.class);

    /**
     * 有参构造器
     */
    public MailSender(String smtp) {
        setSmtpHost(smtp);
        createMimeMessage();
    }

    /**
     * 设置邮件发送的SMTP主机
     */
    public void setSmtpHost(String hostName) {
        if (props == null)
            props = System.getProperties();
        props.put("mail.smtp.host", hostName);
        log.debug("set system properties success ：mail.smtp.host= " + hostName);

    }

    /**
     * 创建邮件对象
     */
    public void createMimeMessage() {
        // 获得邮件会话对象
        session = Session.getDefaultInstance(props, null);
        // 创建MIME邮件对象
        mimeMsg = new MimeMessage(session);
        mp = new MimeMultipart();
        log.debug(" create session and mimeMessage success");
    }

    /**
     * 设置权限鉴定配置
     */
    public void setNeedAuth(boolean need) {
        if (props == null)
            props = System.getProperties();
        if (need) {
            props.put("mail.smtp.auth", "true");
        } else {
            props.put("mail.smtp.auth", "false");
        }
        log.debug("set smtp auth success：mail.smtp.auth= " + need);

    }

    /**
     * 设置发送邮件的主题
     */
    public void setSubject(String subject) throws UnsupportedEncodingException,
            MessagingException {
        mimeMsg.setSubject(MimeUtility.encodeText(subject, "utf-8", "B"));
        log.debug("set mail subject success, subject= " + subject);

    }

    /**
     * @param mailBody 邮件的正文内容
     */
    public void setBody(String mailBody) throws MessagingException {
        BodyPart bp = new MimeBodyPart();
        bp.setContent("" + mailBody, "text/html;charset=utf-8");
        mp.addBodyPart(bp);
        log.debug("set mail body content success,mailBody= " + mailBody);
    }

    /**
     * 添加邮件附件
     */
    public void addFileAffix(String filePath) throws MessagingException {
        BodyPart bp = new MimeBodyPart();
        FileDataSource fileds = new FileDataSource(filePath);
        bp.setDataHandler(new DataHandler(fileds));
        bp.setFileName(fileds.getName());
        mp.addBodyPart(bp);
        log.debug("mail add file success,filename= " + filePath);
    }

    /**
     * 设置发件人邮箱地址
     */
    public void setSender(String sender) throws UnsupportedEncodingException,
            MessagingException {
        //        System.err.println(nickname);
        nickname = MimeUtility.encodeText(nickname, "utf-8", "B");
        //        System.err.println(nickname);
        mimeMsg.setFrom(new InternetAddress(nickname + " <" + sender + ">"));
        log.debug(" set mail sender and nickname success , sender= " + sender
                + ",nickname=" + nickname);
    }

    /**
     * 设置收件人邮箱地址
     */
    public void setReceiver(String receiver) throws AddressException,
            MessagingException {
        mimeMsg.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(receiver));
        log.debug("set mail receiver success,receiver = " + receiver);
    }

    /**
     * 设置抄送人的邮箱地址
     */
    public void setCopyTo(String copyto) throws AddressException,
            MessagingException {
        mimeMsg.setRecipients(Message.RecipientType.CC,
                InternetAddress.parse(copyto));
        log.debug("set mail copyto receiver success,copyto = " + copyto);
    }

    /**
     * 设置发件人用户名密码进行发送邮件操作
     */
    public void sendout() throws MessagingException {
        mimeMsg.setContent(mp);
        mimeMsg.saveChanges();
        Session mailSession = Session.getInstance(props, null);
        Transport transport = mailSession.getTransport("smtp");
        transport.connect((String) props.get("mail.smtp.host"), username,
                password);
        transport.sendMessage(mimeMsg,
                mimeMsg.getRecipients(Message.RecipientType.TO));
        transport.close();
        log.debug(" send mail success");
    }

    /**
     * 注入发件人用户名 ，密码 ，昵称
     */
    public void setNamePass(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;

    }

}
