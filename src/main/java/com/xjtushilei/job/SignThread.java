package com.xjtushilei.job;

import com.jayway.jsonpath.JsonPath;
import com.xjtushilei.domain.AutoSignLog;
import com.xjtushilei.domain.AutoSignUserInfo;
import com.xjtushilei.repository.SignLogRepository;
import com.xjtushilei.utils.PropertyUtil;
import com.xjtushilei.utils.mail.MailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.mail.MessagingException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author shilei
 * @Date 2017/5/31.
 */
public class SignThread extends Thread {

    public static void main(String[] a) throws IOException, MessagingException {
        //        RestTemplate restTemplate = new RestTemplate();
        //        String idCard = "1494470019";
        //        String signUrl = PropertyUtil.getProperty("signurl");
        //
        //        HttpHeaders headers = new HttpHeaders();
        //        headers.add("Content-Type", "application/x-www-form-urlencoded");
        //        MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
        //        bodyMap.add("id", idCard);
        //
        //
        //        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
        //        String result = restTemplate.postForObject(signUrl, requestEntity, String.class);
        //        result = unicodeToUtf8(result);
        //        System.out.println(result);
        //        boolean success = JsonPath.read(result, "$.success");
        //        System.out.println(success);
        //

    }


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private int error;
    private String name;
    private String idCard;
    private String email;
    private String type;
    private SignLogRepository signLogRepository;

    public SignThread(AutoSignUserInfo userInfo, int error, SignLogRepository signLogRepository, String type) {
        this.name = userInfo.getName();
        this.idCard = userInfo.getIdCard();
        this.email = userInfo.getEmail();
        this.error = error;
        this.signLogRepository = signLogRepository;
        this.type = type;

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
                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Type", "application/x-www-form-urlencoded");
                MultiValueMap<String, String> bodyMap = new LinkedMultiValueMap<>();
                bodyMap.add("id", idCard);


                HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(bodyMap, headers);
                html = restTemplate.postForObject(signUrl, requestEntity, String.class);
                html = unicodeToUtf8(html);
            } catch (Exception e) {
                html = "<h1>大概率是因为9楼服务器停网了或者学术部的刷卡系统宕机了。。。或者前去微信公众号签到！</h1>";
                MailUtil.sendMail(email, "[" + LocalDateTime.now() + "][" + name + "]签到【失败】！！！", html);
            }
            boolean success = JsonPath.read(html, "$.success");
            String message = JsonPath.read(html, "$.message");
            signLogRepository.save(new AutoSignLog(new Date(), name, email, type, success + "!  " + message));

        } catch (InterruptedException e) {
            logger.error("延迟启动失败！", e);
        } catch (MessagingException | IOException e) {
            logger.error("邮件发送失败！", e);
        }
    }

    public static String unicodeToUtf8(String theString) {
        char aChar;
        int len = theString.length();
        StringBuffer outBuffer = new StringBuffer(len);
        for (int x = 0; x < len; ) {
            aChar = theString.charAt(x++);
            if (aChar == '\\') {
                aChar = theString.charAt(x++);
                if (aChar == 'u') {
                    // Read the xxxx
                    int value = 0;
                    for (int i = 0; i < 4; i++) {
                        aChar = theString.charAt(x++);
                        switch (aChar) {
                            case '0':
                            case '1':
                            case '2':
                            case '3':
                            case '4':
                            case '5':
                            case '6':
                            case '7':
                            case '8':
                            case '9':
                                value = (value << 4) + aChar - '0';
                                break;
                            case 'a':
                            case 'b':
                            case 'c':
                            case 'd':
                            case 'e':
                            case 'f':
                                value = (value << 4) + 10 + aChar - 'a';
                                break;
                            case 'A':
                            case 'B':
                            case 'C':
                            case 'D':
                            case 'E':
                            case 'F':
                                value = (value << 4) + 10 + aChar - 'A';
                                break;
                            default:
                                throw new IllegalArgumentException(
                                        "Malformed   \\uxxxx   encoding.");
                        }
                    }
                    outBuffer.append((char) value);
                } else {
                    if (aChar == 't')
                        aChar = '\t';
                    else if (aChar == 'r')
                        aChar = '\r';
                    else if (aChar == 'n')
                        aChar = '\n';
                    else if (aChar == 'f')
                        aChar = '\f';
                    outBuffer.append(aChar);
                }
            } else
                outBuffer.append(aChar);
        }
        return outBuffer.toString();
    }
}
