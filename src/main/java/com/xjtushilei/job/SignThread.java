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
    private long nowTime;
    private String grade;
    private SignLogRepository signLogRepository;

    public SignThread(AutoSignUserInfo userInfo, int error, SignLogRepository signLogRepository, String type, Random
            random, long nowTime) {
        this.name = userInfo.getName();
        this.idCard = userInfo.getIdCard();
        this.email = userInfo.getEmail();
        this.grade = userInfo.getGrade();
        this.nowTime = nowTime;
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


            if (isRandomPass(this)) {
                html = "随机不签到行为~";
            } else {
                try {
                    html = restTemplate.getForObject(signUrl + "?id=" + idCard, String.class);
                } catch (Exception e) {
                    html = "<h1>大概率是因为教研室那个刷卡电脑没网或那个电脑坏了或者9楼服务器停网了。</h1>";
                }
            }
            signLogRepository.save(new AutoSignLog(new Date(), name, email, type, html));

        } catch (InterruptedException e) {
            logger.error("延迟启动失败！", e);
        }
    }

    public static boolean isRandomPass(SignThread signThread) {

        /**
         * 1：打卡时间：
         上午 8:30-11:40
         下午 14:20-17:40
         晚上 开始工作时间自由，但要保证三个小时
         2. 次数标准
         研一 10+
         研二 24-28（去掉周五运动时间）
         研三 18+
         */
        long maxLossOf1 = 20;
        long maxLossOf2 = 5;
        long maxLossOf3 = 12;
        long maxLoss = 0;
        int randomRate = 80;
        if (signThread.grade.equals("1")) {
            maxLoss = maxLossOf1;
            randomRate = 34;
        } else if (signThread.grade.equals("2") || signThread.grade.equals("博士")) {
            maxLoss = maxLossOf2;
            randomRate = 80;
        } else if (signThread.grade.equals("3")) {
            maxLoss = maxLossOf3;
            randomRate = 60;
        }

        if (signThread.nowTime >= maxLoss) {
            return false;
        } else if (signThread.random.nextInt(100) >= randomRate) {
            return true;
        } else {
            return false;
        }

    }

}
