package com.xjtushilei.job;

import com.xjtushilei.domain.AutoSignUserInfo;
import com.xjtushilei.utils.PropertyUtil;
import com.xjtushilei.utils.mail.MailUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
public class SignThread extends Thread {

    public static void main(String[] a) throws IOException, MessagingException {
        RestTemplate restTemplate = new RestTemplate();

        String signUrl = PropertyUtil.getProperty("signurl");

        String html = restTemplate.getForObject(signUrl + "?id=" + "1494720355", String.class);
        Document doc = Jsoup.parse(html);
        String table = doc.select("#corner > table").html();
        //        System.out.println(table);
        MailUtil.sendMail("619983341@qq.com", "[签到提醒", table);

    }


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int error;
    private String name;
    private String idCard;
    private String email;

    public SignThread(AutoSignUserInfo userInfo, int error) {
        this.name = userInfo.getName();
        this.idCard = userInfo.getIdCard();
        this.email = userInfo.getEmail();
        this.error = error;

    }

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();

        String signUrl = PropertyUtil.getProperty("signurl");

        try {
            TimeUnit.SECONDS.sleep(error);
            String html = restTemplate.getForObject(signUrl + "?id=" + idCard, String.class);
            MailUtil.sendMail(email, "[" + LocalDateTime.now() + "][" + name + "]的签到提醒", html);
        } catch (InterruptedException e) {
            logger.error("延迟启动失败！", e);
        } catch (MessagingException e) {
            logger.error("邮件发送失败！", e);
        } catch (IOException e) {
            logger.error("读取邮件配置失败！", e);
        }

    }

}
