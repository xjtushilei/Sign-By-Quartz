package com.xjtushilei.job;

import com.xjtushilei.domain.AutoSignLog;
import com.xjtushilei.domain.AutoSignUserInfo;
import com.xjtushilei.repository.SignLogRepository;
import com.xjtushilei.utils.PropertyUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
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
        System.out.println(html);

    }


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int error;
    private String name;
    private String idCard;
    private String email;
    private String type;
    private Random random;
    private SignLogRepository signLogRepository;

    public SignThread(AutoSignUserInfo userInfo, int error, SignLogRepository signLogRepository, String type, Random
            random) {
        this.name = userInfo.getName();
        this.idCard = userInfo.getIdCard();
        this.email = userInfo.getEmail();
        this.error = error;
        this.signLogRepository = signLogRepository;
        this.type = type;
        this.random = random;
    }

    @Override
    public void run() {
        RestTemplate restTemplate = new RestTemplate();

        String signUrl = PropertyUtil.getProperty("signurl");

        try {

            error = random.nextInt(error);
            TimeUnit.MINUTES.sleep(error);
            String html = "";
            try {
                html = restTemplate.getForObject(signUrl + "?id=" + idCard, String.class);
            } catch (Exception e) {
                html = "<h1>大概率是因为教研室那个刷卡电脑没网或那个电脑坏了或者9楼服务器停网了。</h1>";
            }
            signLogRepository.save(new AutoSignLog(new Date(), name, email, type, html));

        } catch (InterruptedException e) {
            logger.error("延迟启动失败！", e);
        }
    }

}
