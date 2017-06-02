package com.xjtushilei.job;

import com.xjtushilei.domain.AutoSignUserInfo;
import com.xjtushilei.utils.MailTemplate;
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
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
public class SignThread extends Thread {

    public static void main(String[] a) throws IOException, MessagingException {
        //        RestTemplate restTemplate = new RestTemplate();
        //
        //        String signUrl = PropertyUtil.getProperty("signurl");
        //
        //        String html = restTemplate.getForObject(signUrl + "?id=" + "1494720355", String.class);
        //        Document doc = Jsoup.parse(html);
        //        String state=doc.getElementById("corner").text();
        //        String table = "";
        //        if(state.indexOf("非法卡")!=-1){
        //            state="非法卡";
        //        }
        //        else {
        //            state="刷卡成功！";
        //            table = doc.select("#corner > table").html();
        //            table = "<table>"+table+"</table>";
        //        }
        //        html = MailTemplate.SignHtml.replace("签到结果",state).replace("table",table);
        //        MailUtil.sendMail("619983341@qq.com","123的结果",html);

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
            error = new Random().nextInt(error);
            TimeUnit.SECONDS.sleep(error);
            String html = "";
            try {
                html = restTemplate.getForObject(signUrl + "?id=" + idCard, String.class);
            } catch (Exception e) {
                html = "<h1>大概率是因为教研室那个刷卡电脑没网或那个电脑坏了或者9楼服务器停网了。</h1>";
                MailUtil.sendMail(email, "[" + LocalDateTime.now() + "][" + name + "]签到【失败】！！！", html);
            }
            Document doc = Jsoup.parse(html);
            String state = doc.getElementById("corner").text();
            String table = "";
            if (state.indexOf("非法卡") != -1) {
                state = "非法卡";
            } else {
                state = "刷卡成功！";
                table = doc.select("#corner > table").html();
                table = "<table>" + table + "</table>";
            }
            html = MailTemplate.SignHtml.replace("签到结果", state).replace("table", table);
            MailUtil.sendMail(email, "[" + LocalDateTime.now() + "][" + name + "]的签到提醒", html);
        } catch (InterruptedException e) {
            logger.error("延迟启动失败！", e);
        } catch (MessagingException | IOException e) {
            logger.error("邮件发送失败！", e);
        }
    }

}
